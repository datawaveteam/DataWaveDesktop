import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

/**
 * Created by Raphael on 1/17/2015.
 */
public class DataWaveDesktop {

    public static void main(String[] args) throws Exception {
        while(true){
            SoundUtil.beep(600,1000);
            Thread.sleep(101);
            SoundUtil.beep(1500,1000);
//            Thread.sleep(101);
//            SoundUtil.createTone(900,100,50);
        }
    }





}
