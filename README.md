## SpringDataElasticsearchDemoApplication
本项目作为spring-boot结合spring-data-elasticsearch通过JPA方式操作ES的使用Demo，旨在学习，有问题欢迎讨论。

## 数据源
在运行项目之前需要先导入 bank 数据到 es 中，数据源来源于 es 官网。具体操作方式请参考
es 官网。[参考链接](https://www.elastic.co/guide/en/elasticsearch/reference/6.2/_exploring_your_data.html#_loading_the_sample_dataset)

## 索引映射信息
在导入 bank 数据之后，可以通过 http://${es}/bank/_mapping 来查看索引的映射信息，这样有助于理解
Account 实体类。

## ES和spring-data-elasticsearch的版本选择
* 确定ES服务端的版本（该项目运行时本地ES运行版本为: 2.1.2，参见项目目录中的 docker-compose.yml）
* 通过查看[spring-data-elasticsearch](https://github.com/spring-projects/spring-data-elasticsearch)明确
spring-data-elasticsearch各版本依赖的具体ES版本，然后在[mvnrepository](http://mvnrepository.com/artifact/org.springframework.data/spring-data-elasticsearch)
中选择合适的spring-data-elasticsearch版本
* 确保上述版本信息明确之后，通过在 pom 中增加如下配置
```xml
<properties>
    <elasticsearch.version>2.2.0</elasticsearch.version>
</properties>

<dependency>
    <groupId>org.springframework.data</groupId>
    <artifactId>spring-data-elasticsearch</artifactId>
    <version>2.0.8.RELEASE</version>
</dependency>
```
* 补充说明:
> 由于该项目的ES版本较低，这里只能选择spring-data-elasticsearch的2.0.8.RELEASE版本，通过在mvnrepository中的查看
依赖树发现，该版本实际要依赖的ES版本为2.2.0，因此我们需要明确指定。这是因为该项目的spring-boot版本选择为1.3.3.RELEASE，通过
查看其依赖树，我们发现它实际加载的ES版本为1.5.2，而且pom中用来指定ES版本的属性名就是elasticsearch.version，因此我们
可以通过覆盖这个字段来指定实际我们想要的ES版本。

## 配置ES链接信息
请参看本项目的配置文件，值得说明的是，这里我们需要用9300端口，而非9200。***这里需要注意***

## 关于启用ES JPA
ES采用JPA方式会和JDBC的JPA产生冲突，导致出现ES JPA接口无法注入到spring aop中，因此我们需要在启动类上显式的说明加载ES JPA，配置如下:
```java
@EnableElasticsearchRepositories(basePackages = "com.koma.spring.springdataelasticsearchdemo.repositories")
```
