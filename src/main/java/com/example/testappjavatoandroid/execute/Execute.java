package com.example.testappjavatoandroid.execute;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;



public class Execute {

    public void lancerAPP(String value){
        System.out.println("lancer app " + value);
        if(!Objects.equals(value, "")){

            try {
                Runtime r = Runtime.getRuntime();
                Process p = null;
                p = r.exec(value);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void lancerSon(String value) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        System.out.println("lancer son " + value);
        File file = new File(value);
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.setFramePosition(0);
        clip.start();

    }

}
