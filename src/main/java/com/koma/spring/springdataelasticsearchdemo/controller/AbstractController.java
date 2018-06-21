package com.koma.spring.springdataelasticsearchdemo.controller;

import com.koma.spring.springdataelasticsearchdemo.model.RestHttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author koma <komazhang@foxmail.com>
 * @date 2018-06-20 15:07
 */
public abstract class AbstractController {
    public ResponseEntity<RestHttpResponse> create() {
        return create(null);
    }

    protected ResponseEntity<RestHttpResponse> create(Object object) {
        RestHttpResponse response = new RestHttpResponse();
        response.setStatus(RestHttpResponse.STATUS_OK);
        response.setEntity(object);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    protected ResponseEntity<RestHttpResponse> createEntities(List<?> entities) {
        RestHttpResponse response = new RestHttpResponse();
        response.setStatus(RestHttpResponse.STATUS_OK);
        response.setEntities(entities);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
