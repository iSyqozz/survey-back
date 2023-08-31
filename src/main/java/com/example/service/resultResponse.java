package com.example.service;

public class resultResponse {
    private boolean success;
    private Object data;
    private Object message;
    // Constructors, getters, setters

    public void setData(Object data) {
        this.data = data;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(Object message) {
        this.message = message;
    }
}