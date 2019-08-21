package chatroomAPP3;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class client {
	ClientFrame cf;
	Socket client;
	Socket client2;
	public BufferedReader Read;
	PrintStream Write;
	Send send;
	client cl;
	public Send getSend() {
		return send;
	}

	public void setSend(Send send) {
		this.send = send;
	}
	String username;
	
	public client()
	{
		
	}
	
    public client(ClientFrame CF){
        this.cf=CF;
        String IP=cf.Server_IP.getText().trim();     //Obtain an IP address;
        int Port=Integer.parseInt(cf.Server_Port.getText().trim());   //Get the port number and convert it to int;
        int Port2 = Port + 1;  
      
        //set the send field and the UI can set send messege.
       

            	cf.Println("Waiting for client access...");
                try 
                {
                	client=new Socket(IP,Port);  
                    cf.Println("The client connects to the server successfully!");  
                    cf.Println("this is text chatroom" );
                }
                catch (IOException e) 
                {
                	cf.Println("Client connection to the server failed!");
                    e.printStackTrace();
                }  
    	            Receive receive = new Receive(client);
    	            Send send= new Send(client);  
    	            
    	            this.setSend(send);
    	            send.setCf(cf);
    	            receive.setCf(cf);
    	            new Thread(send).start();
    	            new Thread(receive).start();      
            
            new Thread(new Runnable()
            {
                @Override
                public void run() 
                {
                	//cf.Println("Waiting for client access...");
                    try 
                    {
                    	client2=new Socket(IP,Port2);  
                        cf.Println("The client2 connects to the server successfully!");  
                    	cf.Println("this is Voice chatroom" );
                    	//create object for audio
                        ClientAudio audio = new ClientAudio(client2);
                        new Thread(audio).start();   
                    } 
                    catch (IOException e) 
                    {
                    	cf.Println("Client connection to the server failed!");
                        e.printStackTrace();
                        }
                    
                	} 
            	}).start();
    } 
}

