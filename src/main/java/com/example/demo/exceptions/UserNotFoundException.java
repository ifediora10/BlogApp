package com.example.demo.exceptions;

import com.example.demo.models.errors.ErrorModel;

import java.util.List;

public class UserNotFoundException extends RuntimeException {

    private List<ErrorModel> errorModelList;
    public UserNotFoundException(List<ErrorModel> errorModelList) {
            this.errorModelList= errorModelList;
    }
}
