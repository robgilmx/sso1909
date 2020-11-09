package com.ksquareinc.sso1909.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class JsonResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String code;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String title;
    private String message;
    private LocalDateTime localDateTime;

    public JsonResponse() {
    }

    public JsonResponse(String code, String title, String message) {
        this.code = code;
        this.title = title;
        this.message = message;
        this.localDateTime = LocalDateTime.now();
    }

    public JsonResponse(HttpStatus httpStatus, String message) {
        this.code = String.valueOf(httpStatus.value());
        this.title = httpStatus.getReasonPhrase();
        this.message = message;
        this.localDateTime = LocalDateTime.now();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
