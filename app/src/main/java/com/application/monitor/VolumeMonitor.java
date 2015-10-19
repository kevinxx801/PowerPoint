package com.application.monitor;

import android.media.MediaRecorder;

import java.io.IOException;

/**
 * VolumeMonitor monitors the android devices microphone and updates
 * a volumeReceiver with the current mic amplitude level
 */
public class VolumeMonitor extends Thread {

    private MediaRecorder mRecorder = null;
    private VolumeReceiver volumeReceiver;
    private static final int PAUSE_AMOUNT = 500;

    /**
     * Constructor for the VolumeMonitor thread
     *
     * @param volumeReceiver - the object that receives the update
     */
    public VolumeMonitor(VolumeReceiver volumeReceiver) {
        this.volumeReceiver = volumeReceiver;
    }

    private void startRecorder() {
        if (mRecorder == null) {
            mRecorder = new MediaRecorder();
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mRecorder.setOutputFile("/dev/null");
            try {
                mRecorder.prepare();
            } catch (IOException e) {
            }
            mRecorder.start();
        }
    }

    /**
     * Stops the recorder in the thread
     */
    private void done() {
        if (mRecorder != null) {
            mRecorder.stop();
            mRecorder.release();
            mRecorder = null;
        }
    }

    /**
     * @return - the amplitude for the recorder
     */
    private double getAmplitude() {
        if (mRecorder != null)
            return mRecorder.getMaxAmplitude();
        else
            return 0;

    }

    public void run() {
        try {
            startRecorder();
            while (true) {
                volumeReceiver.receiveVolumeUpdate((int) getAmplitude());
                Thread.sleep(PAUSE_AMOUNT);
            }
        } catch (InterruptedException ex) {
            done();
        }
    }
}