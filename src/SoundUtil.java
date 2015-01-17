import javax.sound.sampled.*;

/**
 * Created by Stefan on 1/17/2015.
 */
public class SoundUtil {

    public static final int SAMPLERATE = 44100;
    public static void createTone(int hz, int msecs, double vol) throws LineUnavailableException {
        byte[] buffer = new byte[1];

        //First param is float sampleRate
        //Second param is int sample size in bits
        //Third param is the int amount of channels
        //Fourth param is boolean signed
        //Fifth param is enable big endian
        AudioFormat af = new AudioFormat(SAMPLERATE, 8, 1, true, false);


        SourceDataLine sourceDataLine = AudioSystem.getSourceDataLine(af);
        sourceDataLine.open(af);
        sourceDataLine.start();
        for (int i = 0; i < msecs * 8; i++) {
            double angle = i / (SAMPLERATE / hz) * 2.0 * Math.PI;
            buffer[0] = (byte) (Math.sin(angle) * 120.0 * vol);
            sourceDataLine.write(buffer, 0, 1);
        }
        sourceDataLine.drain();
        sourceDataLine.stop();
        sourceDataLine.close();
    }

    public static byte[] getMicrophoneBytes() throws LineUnavailableException {
        AudioFormat format = new AudioFormat(SAMPLERATE,16,1,true,true);
        TargetDataLine microphone;
        Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo();
        for (Mixer.Info info: mixerInfos){
            Mixer m = AudioSystem.getMixer(info);
            Line.Info[] lineInfos = m.getSourceLineInfo();
            for (Line.Info lineInfo:lineInfos){
                System.out.println (info.getName()+"---"+lineInfo);
                Line line = m.getLine(lineInfo);
                System.out.println("\t-----"+line);
            }
            lineInfos = m.getTargetLineInfo();
            for (Line.Info lineInfo:lineInfos){
                System.out.println (m+"---"+lineInfo);
                Line line = m.getLine(lineInfo);
                System.out.println("\t-----"+line);

            }

        }

        Mixer mixer = AudioSystem.getMixer(mixerInfos[0]);

        microphone = (TargetDataLine) mixer.getLine(Port.Info.MICROPHONE);
        byte[] buffer = new byte[4096];
        microphone.read(buffer,0,buffer.length);

        return buffer;
    }


}
