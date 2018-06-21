package com.koma.spring.springdataelasticsearchdemo.services;

import com.koma.spring.springdataelasticsearchdemo.entities.Account;
import com.koma.spring.springdataelasticsearchdemo.model.SearchParam;
import com.koma.spring.springdataelasticsearchdemo.repositories.AccountRepository;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author koma <komazhang@foxmail.com>
 * @date 2018-06-20 15:24
 */
@Service
public class BankService {
    @Autowired
    private AccountRepository accountRepository;

    public List<Account> listAccounts(Pageable pageable) {
        //@NOTE
        //这里的排序字段必须有映射
        Sort sort = new Sort(Sort.Direction.DESC, "balance");
        Pageable pageable1 = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), sort);
        return accountRepository.findAll(pageable1).getContent();
    }

    public Account getById(String id) {
        //@NOTE
        //该方法可以衍生到任意的 findByXXX 查询
        return accountRepository.findOne(id);
    }

    public List<Account> searchAccounts(SearchParam searchParam, Pageable pageable) {
        //@NOTE
        //这里可以使用ES的任何查询语法，这里不再详细讨论
        BoolQueryBuilder queryBuilder = new BoolQueryBuilder();
        if (!StringUtils.isEmpty(searchParam.getFirstname())) {
            queryBuilder.should(new MatchQueryBuilder("firstname", searchParam.getFirstname()));
        }
        if (!StringUtils.isEmpty(searchParam.getLastname())) {
            queryBuilder.should(new MatchQueryBuilder("lastname", searchParam.getLastname()));
        }

        Sort sort = new Sort(Sort.Direction.DESC, "balance");
        Pageable pageable1 = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), sort);
        return accountRepository.search(queryBuilder, pageable1).getContent();
    }

    public void delById(String id) {
        accountRepository.delete(id);
    }

    public Account insert(Account account) {
        return accountRepository.save(account);
    }

    public Account update(String id, Account account) throws Exception {
        Account account1 = getById(id);
        if (account1 == null) {
            throw new Exception("Update Error");
        }
        account.setId(id);
        return accountRepository.save(account);
    }
}
