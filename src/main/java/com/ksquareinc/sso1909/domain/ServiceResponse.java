package com.ksquareinc.sso1909.domain;

import java.io.Serializable;

public class ServiceResponse implements Serializable {

    private String response = "";

    public ServiceResponse() {
    }

    public ServiceResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "ServiceResponse{" +
                "response='" + response + '\'' +
                '}';
    }
}
