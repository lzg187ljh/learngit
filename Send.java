package chatroomAPP3;

import java.io.BufferedReader;
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Send implements Runnable{
	//get data from keyboard
private BufferedReader br;

//stream for sending data
//DataOutputStream write = new DataOutputStream(client.getOutputStream());
private PrintStream Write;
public ClientFrame cf;

public void setCf(ClientFrame cf) {
	this.cf = cf;
}

private String username;

	public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

	public Send() {		//this called first in order to get data from keyborad
	}

public Send(Socket client) {		//set up stream for sending data
	this();
	try {
			Write=new PrintStream(client.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}

private String getMessage() {
	String str="";
		str = cf.Say_Msg.getText().trim(); 
	return str;
}

public void send(String str) {
	Write.println(str);
	Write.flush(); 	//clean the buffer
}



public void run() {

}
}
