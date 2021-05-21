package com.challenge.application.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MathResponse {

    private Integer result;
    private Error error;

    public MathResponse(int result, Error error) {
        this.result = result;
        this.error = error;
    }

    public MathResponse(Error error) {
        this.error = error;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
