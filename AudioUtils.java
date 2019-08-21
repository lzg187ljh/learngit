package chatroomAPP3;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

public class AudioUtils {

    private static AudioFormat af;
    private static Info info;
    private static TargetDataLine td;
    private static Info dataLineInfo;
    private static SourceDataLine sd;

    /**
* Get audio stream data (from pickup)
     * 
     * @return TargetDataLine
     * @throws LineUnavailableException
     */
    public static TargetDataLine getTargetDataLine() throws LineUnavailableException {
        if (td != null) {
            return td;
        } else {
                // 1. Get audio stream data
                //Af is AudioFormat, which is the audio format.
                af = getAudioFormat();
                info = new DataLine.Info(TargetDataLine.class, af);
                //The td here is actually
                td = (TargetDataLine) (AudioSystem.getLine(info));
                //Open a line with the specified format so that the line gets all the required system resources and becomes operational.
                td.open(af);
                //Allow a data row to execute data I/O
                td.start();
            return td;
        }

    }
    /**
 * Get the mixer Write data will play automatically
     * 
     * @return SourceDataLine
     * @throws LineUnavailableException
     */
    public static SourceDataLine getSourceDataLine() throws LineUnavailableException {
        if (sd != null) {
            return sd;
        } else {
                //2. Get data from the audio stream
                dataLineInfo = new DataLine.Info(SourceDataLine.class, af);
                sd = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
                // Open a line with the specified format so that the line gets all the required system resources and becomes operational.
                sd.open(af);
                // Allow a data row to execute data I/O
                sd.start();

            return sd;
        }
    }

    /**
 * Set the parameters of AudioFormat
     * 
     * @return AudioFormat
     */
    public static AudioFormat getAudioFormat() {
        AudioFormat.Encoding encoding = AudioFormat.Encoding.PCM_SIGNED;
        float rate = 8000f;
        int sampleSize = 16;
        String signedString = "signed";
        boolean bigEndian = true;
        int channels = 1;
        return new AudioFormat(encoding, rate, sampleSize, channels, (sampleSize / 8) * channels, rate, bigEndian);
    }

    /**
* Close resources
     */
    public static void close() {
        if (td != null) {
            td.close();
        }
        if (sd != null) {
            sd.close();
        }

    }
}
