package com.application.monitor;

import android.media.MediaRecorder;

import java.io.IOException;

/**
 * VolumeMonitor monitors the android devices microphone and updates
 * a volumeReceiver with the current mic amplitude level
 */
public class VolumeMonitor{

    private MediaRecorder mRecorder = null;
    private static final int PAUSE_AMOUNT = 10;

    /**
     * Constructor for the VolumeMonitor thread
     *
     * @param volumeReceiver - the object that receives the update
     */
    public VolumeMonitor() {
        startRecorder();
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
    public double getAmplitude() {
        if (mRecorder != null)
            return mRecorder.getMaxAmplitude();
        else
            return 0;

    }
}