---
layout: post
title: "Garbage collector"
date: 2019-11-30 21:00:00 +0300
categories: jvm
permalink: garbage-collector
---

# Сборщик мусора (`Garbage collector`)

Сборщик мусора (``Garbage collector - GC`) - это инструмент управления памятью. Он обеспечивает автоматическое управление памятью посредством следующих операций:

- Выделение предметов `young generation` и продвижение пожилых предметов в `old generation`.

- Поиск живых объектов в `old generation` через параллельную (`concurrent (parallel)`) фазу маркировки. `JVM HotSpot ` запускает фазу маркировки, когда общая занятость `Heap` превышает пороговое значение по умолчанию. См. Разделы «Concurrent Mark Sweep (CMS) Collector» и «Garbage-First Garbage Collector».

- Восстановление свободной памяти путем сжатия живых объектов путем параллельного копирования. Смотрите разделы `The Parallel Collector` и  `Garbage-First Garbage Collector`



## Amdahl's law

Amdahl's law (parallel speedup in a given problem is limited by the sequential portion of the problem) implies that most workloads cannot be perfectly parallelized; some portion is always sequential and does not benefit from parallelism.



## 1% time -> 20% loss in throughput

The graph in [Figure 1-1, "Comparing Percentage of Time Spent in Garbage Collection"](https://docs.oracle.com/javase/8/docs/technotes/guides/vm/gctuning/introduction.html#percentage_time_gc) models an ideal system that is perfectly scalable with the exception of garbage collection (GC). The red line is an application spending only 1% of the time in garbage collection on a uniprocessor system. This translates to more than a 20% loss in throughput on systems with 32 processors. The magenta line shows that for an application at 10% of the time in garbage collection (not considered an outrageous amount of time in garbage collection in uniprocessor applications), more than 75% of throughput is lost when scaling up to 32 processors.



## The serial collector VS The parallel collector

The serial collector is usually adequate for most "small" applications (those requiring heaps of up to approximately 100 megabytes (MB (on modern processors). The other collectors have additional overhead or complexity, which is the price for specialized behavior. If the application does not need the specialized behavior of an alternate collector, use the serial collector. One situation where the serial collector is not expected to be the best choice is a large, heavily threaded application that runs on a machine with a large amount of memory and two or more processors. When applications are run on such server-class machines, the parallel collector is selected by default. See the section Ergonomics.



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

**Starting a Concurrent Collection Cycle -> TODO**



# Вопросы

- Какой самый простой способ отчистить мусора?
- Почему он не подходит?
- На какой аксиопер построена сборка мусора с использованием поколений?
- Какой сборщик мусора работает по умолчанию?
- Чем различаются `generations` для разных `GC`?
- 



# Источники

1. [Standard Edition HotSpot Virtual Machine Garbage Collection Tuning Guide](https://docs.oracle.com/javase/8/docs/technotes/guides/vm/gctuning/)
2. [Java HotSpot Garbage Collection](https://www.oracle.com/technetwork/java/javase/tech/index-jsp-140228.html)
3. [Open JDK - Storage Management](http://openjdk.java.net/groups/hotspot/docs/StorageManagement.html)
4. Richard Jones and Rafael Lins, *Garbage Collection: Algorithms for Automated Dynamic Memory Management*, Wiley and Sons (1996), ISBN 0-471-94148-4

