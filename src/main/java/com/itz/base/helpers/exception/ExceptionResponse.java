package com.itz.base.helpers.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ExceptionResponse {
    private String errorMessage;
    private Date timestamp;
    private Boolean isError;

    public ExceptionResponse(String errorMessage) {
        this.errorMessage = errorMessage;
        this.timestamp = new Date();
        this.isError = true;
    }
}
