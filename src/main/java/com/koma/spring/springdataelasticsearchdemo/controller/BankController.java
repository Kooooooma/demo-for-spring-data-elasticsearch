package com.koma.spring.springdataelasticsearchdemo.controller;

import com.koma.spring.springdataelasticsearchdemo.entities.Account;
import com.koma.spring.springdataelasticsearchdemo.model.RestHttpResponse;
import com.koma.spring.springdataelasticsearchdemo.model.SearchParam;
import com.koma.spring.springdataelasticsearchdemo.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author koma <komazhang@foxmail.com>
 * @date 2018-06-20 15:03
 */
@RequestMapping(value = "/demo/account")
@RestController
public class BankController extends AbstractController {
    @Autowired
    private BankService bankService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<RestHttpResponse> list(Pageable pageable) {
        return createEntities(bankService.listAccounts(pageable));
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<RestHttpResponse> search(SearchParam searchParam, Pageable pageable) {
        return createEntities(bankService.searchAccounts(searchParam, pageable));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<RestHttpResponse> get(@PathVariable("id") String id) {
        return create(bankService.getById(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<RestHttpResponse> del(@PathVariable("id") String id) {
        bankService.delById(id);
        return create();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<RestHttpResponse> update(@PathVariable("id") String id,
                                                   @RequestBody Account account) throws Exception {
        return create(bankService.update(id, account));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<RestHttpResponse> insert(@RequestBody Account account) {
        return create(bankService.insert(account));
    }
}
