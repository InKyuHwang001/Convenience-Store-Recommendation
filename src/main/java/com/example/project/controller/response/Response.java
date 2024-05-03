package com.example.project.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Response<T>{

    private String code;
    private T result;

}
