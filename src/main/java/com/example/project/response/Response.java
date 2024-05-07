package com.example.project.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Response<T>{

    private String code;
    private T result;

}
