### SpringBoot整合Elasticsearch
#### 1 引入jar包
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-elasticsearch</artifactId>
</dependency>
```
#### 2 使用方式
```text
(1) 自定义持久层接口继承ElasticsearchRepository<T, ID>接口

(2) 注入ElasticsearchRestTemplate模版

注: ElasticsearchRepository<T, ID>是ElasticsearchRestTemplate的进一步封装
```