package chatroomAPP3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

import chatroomAPP.AudioUtils;

public class ClientAudio implements Runnable{
	
	private TargetDataLine targetDataLine;
	private SourceDataLine sourceDataLine;
	
	private OutputStream out;
    private InputStream in;
    private byte[] bos=new byte[2024];
    private static byte[] bis=new byte[2024];
    
	public ClientAudio(Socket client) {
    	try {
    		out = client.getOutputStream();
            in = client.getInputStream();

            targetDataLine = AudioUtils.getTargetDataLine();

            sourceDataLine = AudioUtils.getSourceDataLine();
		} catch (IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }
	
	public void run() {
    	while(true) {
    		try {
    			//Get audio stream
                int writeLen = targetDataLine.read(bos,0,bos.length);
                //send
                if (bos != null) {
                    //Send the audio acquired by the pickup to the other party
                	
                    out.write(bos,0,writeLen);
                }
                //receive
                int readLen = in.read(bis);
                if (bis != null) {
                    //Play the audio sent by the other party      
                    sourceDataLine.write(bis, 0, readLen);
                }
            } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
}
