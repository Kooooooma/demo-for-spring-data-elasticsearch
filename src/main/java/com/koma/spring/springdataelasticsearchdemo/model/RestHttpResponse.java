package com.koma.spring.springdataelasticsearchdemo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * @author koma <komazhang@foxmail.com>
 * @date 2018-06-20 15:05
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class RestHttpResponse {
    public static final String STATUS_OK = "OK";
    public static final String STATUS_FAIL = "FAIL";

    private String status;
    private Object entity;
    private List<?> entities;
}
