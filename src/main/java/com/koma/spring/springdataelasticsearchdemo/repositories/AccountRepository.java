package com.koma.spring.springdataelasticsearchdemo.repositories;

import com.koma.spring.springdataelasticsearchdemo.entities.Account;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author koma <komazhang@foxmail.com>
 * @date 2018-06-20 15:47
 */
@Repository
public interface AccountRepository extends ElasticsearchRepository<Account, String> {
}
