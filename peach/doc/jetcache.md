### JetCache是一个基于Java的缓存系统封装，提供统一的API和注解来简化缓存的使用。 JetCache提供了比SpringCache更加强大的注解，可以原生的支持TTL、两级缓存、分布式自动刷新，还提供了Cache接口用于手工缓存操作。
### 全部特性
```text
通过统一的API访问Cache系统
通过注解实现声明式的方法缓存，支持TTL和两级缓存
通过注解创建并配置Cache实例
针对所有Cache实例和方法缓存的自动统计
Key的生成策略和Value的序列化策略是可以配置的
分布式缓存自动刷新，分布式锁 (2.2+)
异步Cache API (2.2+，使用Redis的lettuce客户端时)
Spring Boot支持
```
### 依赖哪个jar?
```text
jetcache-anno-api：定义jetcache的注解和常量，不传递依赖。如果你想把Cached注解加到接口上，又不希望你的接口jar传递太多依赖，可以让接口jar依赖jetcache-anno-api。
jetcache-core：核心api，完全通过编程来配置操作Cache，不依赖Spring。两个内存中的缓存实现LinkedHashMapCache和CaffeineCache也由它提供。
jetcache-anno：基于Spring提供@Cached和@CreateCache注解支持。
jetcache-redis：使用jedis提供Redis支持。
jetcache-redis-lettuce（需要JetCache2.3以上版本）：使用lettuce提供Redis支持，实现了JetCache异步访问缓存的的接口。
jetcache-starter-redis：Spring Boot方式的Starter，基于Jedis。
jetcache-starter-redis-lettuce（需要JetCache2.3以上版本）：Spring Boot方式的Starter，基于Lettuce。
```