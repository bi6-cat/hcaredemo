package com.zett.hcaredemo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Response<T> {
    private int status;
    private String message;
    private T data;
    private List<String> errors;

    public Response(int status, String message, T data, List<String> errors) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.errors = errors;
    }

    public Response(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.errors = new ArrayList<>();
    }

}

