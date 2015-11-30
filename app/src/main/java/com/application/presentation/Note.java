package com.application.presentation;

public class Note {

    public String message;
    private int scrollCount;

    public void setMessage(String message) {
        this.message = message;
        this.scrollCount = 0;
    }

    public String getMessage() {


        return this.message;
    }

    public void scroll(){

        scrollCount++;
    }

}
