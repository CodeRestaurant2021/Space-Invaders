package engine;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Manage Audio Effect
 *
 *
 */

public class Audio {

    /**** VARIABLES ****/

    private Clip clip;


    /**** CONSTRUCTEUR ****/

    public Audio(String son){

        try {
            AudioInputStream audio =
                    AudioSystem.getAudioInputStream(getClass().getResource(son));
            clip = AudioSystem.getClip();
            clip.open(audio);
        } catch (Exception e) {}
    }


    /**** METHODES ****/

    public Clip getClip(){return clip;}

    public void play(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void play_once() {
        clip.start();
    }

    public void stop(){clip.stop();}

    public static void playSound(String son){
        Audio s = new Audio(son);
        s.play_once();
    }
}