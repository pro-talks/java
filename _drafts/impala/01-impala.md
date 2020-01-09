# Impala

1. В impala понятие схема и база данных - это одно понятие. 

   > Можно ли выполнять JOIN между таблицами в разных схемах/базах данных? -> ***Да, можно. И это хорошо:)*** Поэтому будет использовать термин схема.

2. Основные запросы 

   ````sql
   CREATE DATABASE IF NOT EXISTS test_schema;
   
   SHOW DATABASES;
   
   CREATE TABLE test_schema.demo (`id` string, `age` DOUBLE, `date` timestamp) STORED AS PARQUET;
   
   SHOW TABLES IN test_schema; 
   
   INSERT OVERWRITE TABLE test_schema.demo VALUES 
   ('1', 18, '1960-01-01 12:00:00.0'), 
   ('2', 19, '1961-01-01 12:10:00.0');
   
   --CREATE TABLE test_schema.demo AS (SELECT * FROM default.your_table);
   
   SELECT * FROM test_schema.demo;
   
   DROP TABLE test_schema.demo;
   
   DROP DATABASE test_schema CASCADE;
   
   SHOW DATABASES;
   ````

3. По дефолту у Impala открытый доступ. То есть строка подключения будет выглядет примерно так

   ````
   jdbc:impala://host:21055
   jdbc:impala://host:21055/default
   jdbc:impala://host:21055/yourSchema
   ````
