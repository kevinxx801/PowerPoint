package com.application.timer;

import com.application.alert.Alert;

public abstract class Timer {

    protected int totalTime;

    protected void getTotalTime() {
        //get the total time from settings
    }

    protected void setTimeLimit() {
        //setTotalTime(time);
    }

    protected void stopTimer() {
        //stop the timer
    }

    protected void startTimer() {
        //start the timer
    }

    protected void throwAlert(Alert alert) {
        //throw a certain type of alert
    }

    private void callAlert(Alert alert) {
        //call to the alert class
    }

    private void setTotalTime(int time) {
        this.totalTime = time;
    }

}
