package com.application.alert;

public class LowTimeAlert implements Alert {

    public String message;

    @Override
    public void sendAlert() {

    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }
}
