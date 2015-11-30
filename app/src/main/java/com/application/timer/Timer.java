package com.application.timer;

import com.application.alert.Alert;
import com.application.alert.LowTimeAlert;
import com.application.alert.LowTimeAlert.TimeAlert;

public class Timer {

    private long startTime;
    protected long totalTime = 0;
    protected long alertTime = 0;
    private boolean alertOccurence = false;
    private Alert lta;
    private long elapsed = 0;
    private TimeAlert alertType;

    public Timer(boolean setAlert, TimeAlert type){
        startTime = 0;
        if (setAlert){
            this.alertType = type;
            this.lta = new LowTimeAlert(type);
        } else {
            this.alertType = null;
        }
        lta.setMessage(timeLeftString() + " remaining");

    }

    public String getElapsedTimeString()
    {
        if(startTime == 0){
            return "--";
        }
        //Get the current time.
        long currentTime = System.currentTimeMillis();
        //Find the elapsed time
        this.elapsed = currentTime - startTime;
        //Find the elapsed time in minutes and seconds
        long seconds = elapsed / 1000;
        long minutes = seconds / 60;
        seconds = seconds % 60;
        //Format the output
        String clock = String.format("%d:%02d", minutes, seconds);



        return clock;
    }

    public void setTimeLimit(int time) {
        setTotalTime(time);
        alertTime = (int)(totalTime * .9);
    }

    public String timeLeftString(){
        long timeLeft = totalTime - startTime;
        long seconds = timeLeft / 1000;
        long minutes = seconds / 60;
        seconds = seconds%60;

        String clock = String.format("%d:%02d", minutes, seconds);

        return clock;
    }

    //"Stop" the timer.
    protected void stopTimer() {
        startTime = 0;
    }

    // Can be used to start and restart a timer.
    public void startTimer() {
        //Start the timer.
        startTime = System.currentTimeMillis();
    }

    protected void throwAlert(Alert alert) {
        lta.sendAlert();
        //throw a certain type of alert
    }

    private void callAlert(Alert alert) {
        //call to the alert class
    }

    private void setTotalTime(int time) {
        this.totalTime = time;
    }

    public long getTotalTime(){
        return this.totalTime;
    }

    public long getAlertTime(){
        return this.alertTime;
    }

    public long getElapsedTime(){
        return this.elapsed;
    }

    public boolean getAlertOccurance(){
        return this.alertOccurence;
    }

    public void setAlertOccurance(boolean status){
        this.alertOccurence = status;
    }

    public TimeAlert getAlertType(){
        return this.alertType;
    }

}
