---
layout: post
title: "Bean Utils"
date: 2019-08-01 12:00:00 +0300
categories: libraries
permalink: bean-utils
---

# Bean Utils

## Зачем нужен?

- Позволяет клонировать объекты, использую *Reflection* и *Introspection API* (`java.lang.reflect` и `java.beans`)



## Клонирование свойств

````java
public class Box {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
````

Для клонирования объекта достаточно написать следующий код

````java
@Test
public void copyProperties() throws InvocationTargetException, IllegalAccessException {
    Box orig = new Box();
    orig.setName("Box");

    Box dest = new Box();
    BeanUtils.copyProperties(dest, orig);

    assertEquals("Box", dest.getName());
}
````

> Не будет копировать, если будет несколько getter'ов, например `getName()` and `isName()`. 

```java
-Djava.util.logging.config.file=/D:/config.properties
```



# Логировать 

Создать файл `log-config.properties`

````properties
# The following creates handler
handlers=java.util.logging.ConsoleHandler
# Set the default logging level for the root logger
.level=SEVERE
# log level for the "com.example" package
org.apache.commons.beanutils.level=FINE
# Set the default logging level
java.util.logging.ConsoleHandler.level=ALL
# Set the default formatter
java.util.logging.ConsoleHandler.formatter=java.util.logging.SimpleFormatter
````

Запустить jvn c флагом `-Djava.util.logging.config.file=C:\...\log-config.properties`



# Вопросы

1. Что будет?

   `````java
   public class Box2 {
   
       private String name;
   
       public String getName() {
           return name;
       }
   
       public void setName(String name) {
           this.name = name;
       }
   
       public boolean isName() {
           return "Name".equalsIgnoreCase(name);
       }
   }
   
   //run this
   @Test
   public void copyProperties2() throws InvocationTargetException, IllegalAccessException {
       Box2 orig = new Box2();
       orig.setName("Box");
   
       Box2 dest = new Box2();
       BeanUtils.copyProperties(dest, orig);
   
       assertEquals("Box", dest.getName());
   }
   `````

   - Выпадет `AssertionError`, так как name будет null. Так как объект содержит 2а getter'a.



# Источники

1. https://www.tutorialspoint.com/java_beanutils>