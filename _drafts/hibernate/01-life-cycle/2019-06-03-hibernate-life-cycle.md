---
layout: post
title: "Hibernate Life Cycle"
date: 2019-06-03 12:00:00 +0300
categories: hibernate
permalink: hibernate-lifecycle
---

# Hibernate 

## Состояния Entity в Hibernate

1. **Persistent** (из базы) - объект из базы. Есть ассоциация между объектом и базой.
2. **Transient** (не из базы) - новый объект. Нет ассоциации между объектом и базой.
3. **Detached** (из базы, но с изменениями) - измененый объект из базы. Есть ассоциация с базой, но скорее всего нужно мержиться.


Что такое однонаправленная и двунаправленая связь?


## Источники

1. [Hibernate: save, persist, update, merge, saveOrUpdate]( https://www.baeldung.com/hibernate-save-persist-update-merge-saveorupdate)