import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundClass {

    Clip clip;
    File musicPath;
    AudioInputStream audioInput;

    void playLoopedMusic(String musicLocation){
        try {
            musicPath = new File(musicLocation);
            if (musicPath.exists()) {
                audioInput = AudioSystem.getAudioInputStream((musicPath));
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                clip.loop((Clip.LOOP_CONTINUOUSLY));
            } else {
                System.out.println("cant find file");
                }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    void stopLoopedMusic(){
        try {
            if (musicPath.exists()) {
                clip.stop();
            } else {
                System.out.println("cant find file");
                }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    void playMusic (String musicLocation){

        try {
            File musicPath = new File(musicLocation);

            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream((musicPath));
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            } else {
                System.out.println("cant find file");
                }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    void playGameOver (String musicLocation){
        try {
            File musicPath = new File(musicLocation);

            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream((musicPath));
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            } else {
                System.out.println("cant find file");
                }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
