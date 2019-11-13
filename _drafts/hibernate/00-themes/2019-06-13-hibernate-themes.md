---
layout: post
title: "Hibernate themes"
date: 2019-06-13 12:00:00 +0300
categories: hibernate
permalink: hibernate-themes
---

# Hibernate 

## Hibernate - Cache 1 level

Если объект уже был вытянут из базы, то существует сессия, которая будет хранить объект. И если запросить объект еще раз, то объект не будет вытащен

Чтоб отчистить сессию можно вызвать, например, EntityManager.clear()

## OneToMany and ManyToOne

https://thoughts-on-java.org/best-practices-many-one-one-many-associations-mappings/

## Hibernate нуждается в конструкторе без параметров

`Caused by: org.hibernate.InstantiationException: No default constructor for entity`

## Источники

1. https://habr.com/ru/post/416851/
2. Видео крутого чувака про hibernate - https://www.youtube.com/watch?v=b52Qz6qlic0&feature=youtu.be
3. https://hibernate.org/orm/releases/5.4/
4. Оффициальная документация - https://docs.jboss.org/hibernate/stable/orm/userguide/html_single/Hibernate_User_Guide.html#associations-one-to-one
5. Офф док 2 - <https://docs.jboss.org/hibernate/orm/5.4/quickstart/html_single/>
