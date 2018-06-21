package com.koma.spring.springdataelasticsearchdemo.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author koma <komazhang@foxmail.com>
 * @date 2018-06-20 15:36
 */
@Data
@Document(indexName = "bank", type = "account")
public class Account {
    //主键字段: 插入数据时，该字段可指定也可以让ES自己随机生成
    @Id
    private String id;

    //映射字段
    private long accountNumber;
    private long balance;
    private long age;
    private String address;
    private String city;
    private String email;
    private String employer;
    private String firstname;
    private String lastname;
    private String gender;
    private String state;
}
