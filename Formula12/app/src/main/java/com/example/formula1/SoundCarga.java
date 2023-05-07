package com.example.formula1;

import android.media.MediaPlayer;

import java.io.IOException;

public class SoundCarga extends Thread{

    private MediaPlayer soundf1;

    public SoundCarga(MediaPlayer soundf1) {
        this.soundf1 = soundf1;
    }

    @Override
    public void run() {
        super.run();
        //aquí lo inicio
        soundf1.start();
        //espero lo que toast demora

        try {

            Thread.sleep(2200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        soundf1.stop();

    }
}
