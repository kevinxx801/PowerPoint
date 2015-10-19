package com.application.monitor;

/**
 * Provides an interface that will allow the VolumeMonitor thread to update
 * an object that implements this interface with the current amplitude
 */
public interface VolumeReceiver {

    /**
     * This method is called by the VolumeMonitor whenever it has a
     * new value for the volume
     *
     * @param volume - The volume update value
     */
    public void receiveVolumeUpdate(int volume);
}