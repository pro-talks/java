---
layout: post
title: "Garbage collector"
date: 2019-11-30 21:00:00 +0300
categories: jvm
permalink: garbage-collector
---

# Сборщики мусора (`Garbage collector`) в `JVM HotSpot `

Сборщик мусора (``Garbage collector - GC`) - это инструмент управления памятью, который

- **выделяет память** для новых объектов и помещает их в `young generation`,
- **перемещает живые объекты** в `old generation`,
- **ищет живые объекты** в `generations`, используя параллельные стратегии маркировки,
- **восстанавливает память**, удаляя и сжимая объекты, используя параллельное копирование.



## Закон Амдала и потеря пропускной способности

Закон Амдала (ускорение с помощью параллельного вычисления ограничено размером последовательной части программы) подразумевает что часть рабочих нагрузок не может быть распараллелена и выполняется всегда последовательно (работы при сборке мусора).

Математическое выражение закона Амдала, выглядит следующим образом, где

- `Sp` - возможно ускорение в количество раз,
- `a` - непараллезируемая часть работ, 
- `p` - количество процессоров.

![S_{p}={\cfrac  {1}{\alpha +{\cfrac  {1-\alpha }{p}}}}](https://wikimedia.org/api/rest_v1/media/math/render/svg/3d6be545af375c8517c584f7d0364b75892979a3)

Например, в идельной системе (`a` = 0) ускорение пропроционально количеству процессоров, например при `p` = 32, ускорение будет в 32 раза.

Если сборка мусора занимает 1% времени на однопроцессорной машине. То это приведет к потери пропускной способности более чем на 20% (23,6%) на 32ух процессорах.

Если сборка мусора занимает 10% времени (что не считается очень большим значением для однопроцессорных систем), то это приведет к более чем 75% (75,6%) потери пропускной способности на 32ух процессорах.

![](https://docs.oracle.com/javase/8/docs/technotes/guides/vm/gctuning/img/jsgct_dt_005_gph_pc_vs_tp.png)

То есть незначительные проблемы пропускной способности, могут привести к большим проблемам (`bottleneck`) при масштабировании. С другой стороны, небольшие улучшения могут дать большой скачок в производительности. Поэтому для больших систем, целесообразно выбрать правильных `GC` и настроить его.



## Последовательный сборщик (`Serial collector`)

Последовательный сборщик (`Serial collector`) обычно подходит для небольших приложений на однопроцессорных машинах с размером `heap` примерно до 100MB. 

**TODO**

Использование параллельных коллекторов не рекомендуется в таких условиях, так как они имеют дополнительные накладные расходы и сложность, что является ценой за специализированное поведени.



# Настройка `GC`

## Эргономика

Эргономика - это процесс настройки `JVM` и `GC` с целью использовать оптимальный режим работы:

- повысить пропускную способность,
- уменьшить время пауз (отклика),
- уменьшить количество используемых ресурсов.



## Дефолтный выбор

`JVM` предоставляет платформо-зависимый автоматический выбор:

- сборщика мусора (`GC`),
- размера `heap`,
- компилятора времени выполения (`runtime compiler`).

Дефолтный выбор соответствует нуждам различных приложений и требует минимальной настройки через командную строку.

Для автоматического выбора значений `JVM` оперделяет операционную систему и параметры машины.

### Класс "Сервер" (`Server-class machine`) 

**TODO only 32-bit**

Машина определяется в класс `Server-class machine`, если имеет

- 2 или более физических процессоров,

- 2 или болee GB оперативной памяти.

Для `Server-class machine` по дефолту выбирается:

- `Throughput garbage collector`,

- Начальных хип (`Min heap`) = 1/64 оперативной памяти до 1GB,
- Максимальный хип (`Max heap`)  = 1/4 оперативной памяти до 1GB,
- Серверный компилятор времени выполнения (`Server runtime compiler`)



# Сборщик The Parallel Collector







## Настройка на основе поведения (`Behavior-Based Tuning`)

Эргономика `JVM` предоставляет возможность автоматической динамической настройки `JMV` и `GC` на основе поведения приложения (`behavior-based tuning`). 











В добавок, настройка на основе поведения (`behavior-based tuning`) динамически настраивает размер `Heap` под приложение.









Используйте дефолтные настройки прежде чем использовать более детальные настройки.







Server

## Garbage Collector, Heap, and Runtime Compiler Default Selections

- 



# Behavior-Based Tuning

`Java SE` два параметра настройки сборки мусора, которые основаны на достижении определенного поведения приложения: 

- цель максимального времени паузы,
- цель пропускной способности приложения.

Эти два параметра недоступны в других сборщиках. Обратите внимание, что такое поведение не всегда может быть достижимо. Приложению требуется куча, достаточно большая, чтобы хотя бы хранить все оперативные данные. Кроме того, минимальный размер кучи может помешать достижению этих желаемых целей.



## Цель, максимальное время паузы

Время паузы - это продолжительность, в течение которой сборщик мусора останавливает приложение и восстанавливает пространство, которое больше не используется. Цель максимального времени паузы состоит в том, чтобы ограничить самый длинный из этих пауз. Среднее время для пауз и дисперсии в среднем поддерживается сборщиком мусора. Среднее значение берется с начала выполнения, но взвешивается таким образом, чтобы более поздние паузы учитывались в большей степени. Если среднее значение плюс дисперсия времени паузы превышает максимальное значение времени паузы, то сборщик мусора считает, что цель не достигнута.

Максимальное время паузы указывается с помощью параметра командной строки `XX:MaxGCPauseMillis=<nnn>`. Это интерпретируется как подсказка сборщику мусора о желаемом времени паузы `<nnn>` миллисекунд. Сборщик мусора будет корректировать размер кучи Java и другие параметры, связанные со сборкой мусора, чтобы сделать паузы сбора мусора короче, чем `<nnn>` миллисекунд. По умолчанию нет максимальной цели времени паузы. Эти корректировки могут привести к более частому запуску сборщика мусора, что приведет к снижению общей пропускной способности приложения. Сборщик мусора пытается достичь цели времени паузы, а после цели пропускной способности. В некоторых случаях, однако, желаемая цель времени паузы не может быть достигнута.



## Цель, максимальная пропускная способность

Цель пропускной способности измеряется с точки зрения времени, затраченного на сбор мусора, и времени, потраченного за пределами сбора мусора (называемого временем приложения). Цель задается параметром командной строки `-XX: GCTimeRatio = <nnn>`. Отношение времени сборки мусора к времени приложения составляет `1 / (1 + <nnn>)`. Например, `-XX: GCTimeRatio = 19` устанавливает цель в `1/20` или 5% от общего времени для сбора мусора.

Время, затрачиваемое на сборку мусора - это общее время для сборок как молодого, так и старого поколений. Если цель пропускной способности не достигается, размеры поколений увеличиваются, чтобы увеличить время, в течение которого приложение может выполняться между коллекциями



## Цель, отпечаток

Если были достигнуты цели пропускной способности и максимального времени паузы, то сборщик мусора уменьшает размер кучи до тех пор, пока не будет достигнута одна из целей (без исключения цели пропускной способности). Цель, которая не достигнута, затем решается.



# Стратегия настройки (`Tuning Strategy`)

Не выбирайте максимальное значение для кучи, если вы не знаете, что вам нужна куча, превышающая максимальный размер кучи по умолчанию. Выберите целевую пропускную способность, достаточную для вашего приложения.

Куча будет расти или уменьшаться до размера, который будет поддерживать выбранную цель пропускной способности. Изменение в поведении приложения может привести к увеличению или уменьшению кучи. Например, если приложение начинает выделять с более высокой скоростью, куча будет расти, чтобы поддерживать ту же пропускную способность.

Если размер кучи увеличивается до максимального размера, а цель пропускной способности не достигается, максимальный размер кучи слишком мал для цели пропускной способности. Установите для максимального размера кучи значение, близкое к общему объему физической памяти на платформе, но не вызывающее перестановки приложения. Запустите приложение еще раз. Если цель по пропускной способности все еще не достигнута, тогда цель для времени приложения слишком высока для доступной памяти на платформе.

Если цель пропускной способности может быть достигнута, но есть паузы, которые являются слишком длинными, то выберите максимальную цель времени паузы. Выбор цели максимального времени паузы может означать, что ваша цель по пропускной способности не будет достигнута, поэтому выберите значения, которые являются приемлемым компромиссом для приложения.

Типично, что размер кучи будет колебаться, поскольку сборщик мусора пытается удовлетворить конкурирующие цели. Это верно, даже если приложение достигло устойчивого состояния. Давление для достижения цели по пропускной способности (которая может потребовать большей кучи) конкурирует с целями за максимальное время паузы и минимальную занимаемую площадь (что может потребовать небольшой кучи).





## The parallel collector

Ситуация когда `Serial collector` не является лучшим выбором, это большое многопоточное приложение, которое выполняется на машине с большим количеством памяти и двумя и более процессорами. Когда приложение запущено на такой машине, то параллельный сборщик (`parallel collector`) выбирается по умолчанию.







## Notes

1. The Java HotSpot VM triggers the marking phase when the total Java heap occupancy exceeds the default threshold. See the sections [Concurrent Mark Sweep (CMS) Collector](https://docs.oracle.com/javase/8/docs/technotes/guides/vm/gctuning/cms.html#concurrent_mark_sweep_cms_collector) and [Garbage-First Garbage Collector](https://docs.oracle.com/javase/8/docs/technotes/guides/vm/gctuning/g1_gc.html#garbage_first_garbage_collection).

















## The garbage collector, heap size, and runtime compiler

The JVM provides platform-dependent default selections for the garbage collector, heap size, and runtime compiler.



## Maximum pause time goal and application throughput goal



## Maximum Pause Time Goal



## Throughput Goal



## Tuning Strategy

Do not choose a maximum value for the heap unless you know that you need a heap greater than the default maximum heap size. Choose a throughput goal that is sufficient for your application.



# Generations

## An object is considered garbage

An object is considered garbage when it can no longer be reached from any pointer in the running program.



## Most objects survive for only a short period of time

Efficient collection is made possible by focusing on the fact that a majority of objects "die young."



## The central tenet of this document:

If garbage collection becomes a bottleneck, you will most likely have to customize the total heap size as well as the sizes of the individual generations. Check the verbose garbage collector output and then explore the sensitivity of your individual performance metric to the garbage collector parameters.



## The young generation consists of eden and two survivor spaces

- The young generation consists of eden and two survivor spaces. 

- Most objects are initially allocated in eden. 

- One survivor space is empty at any time, and serves as the destination of any live objects in eden; the other survivor space is the destination during the next copying collection. 

- Objects are copied between survivor spaces in this way until they are old enough to be tenured (copied to the tenured generation).



## Performance Considerations

- Throughput
- Pauses

In general, choosing the size for a particular generation is a trade-off between these considerations. For example, a very large young generation may maximize throughput, but does so at the expense of footprint, promptness, and pause times. Young generation pauses can be minimized by using a small young generation at the expense of throughput. The sizing of one generation does not affect the collection frequency and pause times for another generation.



## Measurement

### Команда `-verbose:`

Использование команды `-verbose:`



## Sizing the Generations

- The Young Generation
- The Survivor Space Sizing
- The tenured generation
- Virtual space



## Available Collectors

- The serial collector
  - Выбирается на определенном оборудовании, явно может быть назначен `-XX:+UseSerialGC`
- The parallel collector (also known as the throughput collector)
  - Выбирается по дефолту на многопроцессорных машинах со средним или большим размером `Heap`, может быть назначен `-XX:+UseParallelGC`
  - Parallel compaction 
  - Two concurrent collectors. Use the option `-XX:+UseConcMarkSweepGC` to enable the CMS collector or `-XX:+UseG1GC` to enable the G1 collector.



## Selecting a Collector

Important





##  The Parallel Collector



## The Mostly Concurrent Collectors



## Concurrent Mark Sweep (CMS) Collector



## Garbage-First Garbage Collector



## Garbage-First Garbage Collector Tuning





# Вопросы

- Когда выбор `GC` имеет значение?
  - For some applications, the answer is never. That is, the application can perform well in the presence of garbage collection with pauses of modest frequency and duration. However, this is not the case for a large class of applications, particularly those with large amounts of data (multiple gigabytes), many threads, and high transaction rates.
- Как звучит закон Амдала?
  - Amdahl's law (parallel speedup in a given problem is limited by the sequential portion of the problem) implies that most workloads cannot be perfectly parallelized; some portion is always sequential and does not benefit from parallelism.
- Как влияет сборка мусора на потерю пропускной способности на малых и больших системах? И почему?
- "Если сборка мусора занимает 1% времени на однопроцессорной машине. То это приведет к 20% потери пропускной способности при 32 процессорах". Почему так происходит?
  - Связано с законом Амдала, то в идеальных условиях можно ускороить программу в n раз, где n - это количество процессоров. Но каждая задача имеет не параллезируюмую работы (сборка мусора), это снижает потенциальную пропускную способность.
- Когда стоит и не стоит использовать `Serial collector`?
- Когда `Serial collector` выбирается по умолчанию?
- Какой самый простой способ отчистить мусора?
- Почему он не подходит?
- На какой аксиопер построена сборка мусора с использованием поколений?
- Какой сборщик мусора работает по умолчанию?
- Чем различаются `generations` для разных `GC`?
- HotSpot VM Frequently Asked Questions (FAQ) -> https://www.oracle.com/technetwork/java/hotspotfaq-138619.html



# Источники

1. [Standard Edition HotSpot Virtual Machine Garbage Collection Tuning Guide](https://docs.oracle.com/javase/8/docs/technotes/guides/vm/gctuning/)
2. [Java HotSpot Garbage Collection](https://www.oracle.com/technetwork/java/javase/tech/index-jsp-140228.html)
3. [Open JDK - Storage Management](http://openjdk.java.net/groups/hotspot/docs/StorageManagement.html)
4. Richard Jones and Rafael Lins, *Garbage Collection: Algorithms for Automated Dynamic Memory Management*, Wiley and Sons (1996), ISBN 0-471-94148-4

