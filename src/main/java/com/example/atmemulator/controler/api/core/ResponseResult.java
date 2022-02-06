package com.example.atmemulator.controler.api.core;

import java.util.List;

public class ResponseResult<T> {
    private Integer code;
    private T data;
    private List<T> dataList;
    private String message;


}
