package chatroomAPP3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Receive implements Runnable {
	
	  //stream for receiving data 
    //DataInputStream read = new DataInputStream(client.getInputStream());
    private BufferedReader Read;
    public ClientFrame cf;
    
    public void setCf(ClientFrame cf) {
		this.cf = cf;
	}

	public Receive(Socket client) {
    	try {
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
    
	public void run() {
		while(true) {
		 cf.Println(this.getMessage());
		}
	}
}
