import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Stefan on 1/17/2015.
 */
public class SoundUtil {

    Thread t;
    int sr = 44100;
    boolean isRunning = true;

    public void startTone()
    {
        isRunning = true;
        t = new Thread() {
            public void run() {
                // set process priority
                setPriority(Thread.MAX_PRIORITY);
                int buffsize = AudioTrack.getMinBufferSize(sr,
                        AudioFormat.CHANNEL_OUT_MONO,
                        AudioFormat.ENCODING_PCM_16BIT);
                // create an audiotrack object
                AudioTrack audioTrack = new AudioTrack(
                        AudioManager.STREAM_MUSIC, sr,
                        AudioFormat.CHANNEL_OUT_MONO,
                        AudioFormat.ENCODING_PCM_16BIT, buffsize,
                        AudioTrack.MODE_STREAM);

                short samples[] = new short[buffsize];
                int amp = 10000;
                double twopi = 8. * Math.atan(1.);
                double fr = 440.f;
                double ph = 0.0;
                // start audio
                audioTrack.play();

                while (isRunning) {

                    for (int i = 0; i < buffsize; i++) {
                        samples[i] = (short) (amp * Math.sin(ph));
                        ph += twopi * fr / sr;
                    }
                    audioTrack.write(samples, 0, buffsize);
                }
                audioTrack.stop();
                audioTrack.release();
            }
        };
        t.start();
    }

    public void stopTone(){
        isRunning = false;
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t = null;
    }










    public static byte[] getMicrophoneBytes() throws LineUnavailableException {
        AudioFormat format = new AudioFormat(SAMPLERATE, 16, 1, true, true);
        TargetDataLine microphone;
        Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo();
        for (Mixer.Info info : mixerInfos) {
            Mixer m = AudioSystem.getMixer(info);
            Line.Info[] lineInfos = m.getSourceLineInfo();
            for (Line.Info lineInfo : lineInfos) {
                System.out.println(info.getName() + "---" + lineInfo);
                Line line = m.getLine(lineInfo);
                System.out.println("\t-----" + line);
            }
            lineInfos = m.getTargetLineInfo();
            for (Line.Info lineInfo : lineInfos) {
                System.out.println(m + "---" + lineInfo);
                Line line = m.getLine(lineInfo);
                System.out.println("\t-----" + line);

            }

        }

        Mixer mixer = AudioSystem.getMixer(mixerInfos[0]);

        microphone = (TargetDataLine) mixer.getLine(Port.Info.MICROPHONE);
        byte[] buffer = new byte[4096];
        microphone.read(buffer, 0, buffer.length);

        return buffer;
    }


}
