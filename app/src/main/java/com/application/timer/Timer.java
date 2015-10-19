package com.application.timer;

import com.application.alert.Alert;

public class Timer {

    private long startTime;
    protected int totalTime;

    public Timer(){
        startTime = 0;
    }

    protected String getElapsedTimeString()
    {
        if(startTime == 0){
            return "--";
        }
        //Get the current time.
        long currentTime = System.currentTimeMillis();
        //Find the elapsed time
        long elapsed = currentTime - startTime;
        //Find the elapsed time in minutes and seconds
        long seconds = elapsed / 1000;
        long minutes = seconds / 60;
        seconds = seconds%60;
        //Format the output
        String clock = String.format("%d:%02d", minutes, seconds);
        return clock;
    }

    protected void setTimeLimit() {
        //setTotalTime(time);
    }

    //"Stop" the timer.
    protected void stopTimer() {
        startTime = 0;
    }

    // Can be used to start and restart a timer.
    protected void startTimer() {
        //Start the timer.
        startTime = System.currentTimeMillis();
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
