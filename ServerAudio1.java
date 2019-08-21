package chatroomAPP3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

public class ServerAudio1 implements Runnable{
	
	private TargetDataLine targetDataLine;
	private SourceDataLine sourceDataLine;
	
	private OutputStream out;
    private InputStream in;
    private byte[] bos=new byte[2024];
    private static byte[] bis=new byte[2024];

	public ServerAudio1(Socket client) {
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
	
	private int ReadAudio() {
    	//************audio
		//Get audio stream
		int readLen=0;
		try {
			readLen = in.read(bis);
			if (bis != null) {
	            //Play the audio sent by the other party	         
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return readLen;
	}
	
	private void WriteAudio(int readLen) {
		
		try {
			out.write(bis,0,readLen);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	
public void run() {
	while(true) {
		//we need to sent to everyone but me,it is how a chatroom be constructed	 
		//Receive
		int readLen=ReadAudio();
		
		List<ServerAudio1> audioList=server.audioList;
		//send
		for(int i=0;i<audioList.size();i++) {
			if(audioList.get(i)==this) {		
				continue;
			}
			if (bis!= null) {
				audioList.get(i).WriteAudio(readLen);
		    }
		}
	}
}
}
