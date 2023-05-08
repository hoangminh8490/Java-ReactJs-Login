package com.example.demo.dto;

public class ErrorReponse {

    private String error;
    private String message;

    public String getError() {
        return error;
    }

    public ErrorReponse(String error, String message) {
        this.error = error;
        this.message = message;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
