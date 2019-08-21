package chatroomAPP3;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

/*this will replace the sending and receiving part in server.
for each time in while loop,a new object channel will be created,
 the data received from client will be send
back to client*/
public class MyChannel implements Runnable{
	
	private PrintStream Write;
    private BufferedReader Read;
    ServerFrame sf;
    
    public void setSf(ServerFrame sf) {
		this.sf = sf;
	}

	public MyChannel(Socket client, ServerFrame sf) {
    	try {
    		sf.Println("client "+server.i+" enter chatroom.");
    	       
			Write=new PrintStream(client.getOutputStream());
			Read=new BufferedReader(new InputStreamReader(client.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private String getMessage() {
    	String str="";
    	try {
			str=Read.readLine();	// read a string from standard input
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return str;
    }
    
    private void send(String str) {
    	Write.println(str);
    	Write.flush(); 	//clean the buffer
    }
   
    
    public void run() {
    	while(true) {
    		//we need to sent to everyone but me,it is how a chatroom be constructed
    		//this.send(getMessage());		
    		String str=this.getMessage();		//get message must go first
    		List<MyChannel> list=server.list;
    		for(int i=0;i<list.size();i++) {
    			if(list.get(i)==this) {		//don't sent to itself
    				continue;
    			}
    			
    			list.get(i).send(str);
    		}
    		
    		
    	}
    }
}
