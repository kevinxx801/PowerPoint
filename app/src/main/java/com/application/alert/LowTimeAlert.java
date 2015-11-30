package com.application.alert;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;

import com.example.kevin.powerpoint.MainActivity;

import java.sql.Time;

public class LowTimeAlert implements Alert {

    public String message;
    private TimeAlert alertType;

    public enum TimeAlert{
        Visual,
        Audio
    }

    public TimeAlert getAlertType(){
        return this.alertType;
    }

    public LowTimeAlert(TimeAlert alertType){
        this.alertType = alertType;
    }

    @Override
    public void sendAlert() {
        if(this.alertType == TimeAlert.Visual){
            sendVisualAlert();
        } else if (this.alertType == TimeAlert.Audio){
            sendAudioAlert();
        } else {
            return;
        }
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    private void sendVisualAlert(){

    }

    private void sendAudioAlert(){

    }
}
