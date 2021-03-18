package com.joljak.base.dto;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public class ResponseDto {
    private boolean result = false;
    private String code;
    private String message;
    private List<String> errors;
    private String token;
    private String refreshToken;
    private BaseDto returnDto;

    public ResponseDto() {
        super();
    }

    public ResponseDto(final HttpStatus status, final String message, final List<String> errors) {
        super();
        this.message = message;
        this.errors = errors;
    }

    public ResponseDto(final HttpStatus status, final String message, final String error) {
        super();
        this.message = message;
        errors = Arrays.asList(error);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BaseDto getReturnDto() {
        return returnDto;
    }

    public void setReturnDto(BaseDto returnDto) {
        this.returnDto = returnDto;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(final List<String> errors) {
        this.errors = errors;
    }

    public void setError(final String error) {
        errors = Arrays.asList(error);
    }

}