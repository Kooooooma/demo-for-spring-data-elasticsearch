package com.koma.spring.springdataelasticsearchdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "com.koma.spring.springdataelasticsearchdemo.repositories")
public class SpringDataElasticsearchDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataElasticsearchDemoApplication.class, args);
    }
}
