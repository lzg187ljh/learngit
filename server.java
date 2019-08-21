package chatroomAPP3;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class server extends Thread{
		//create a arraylist to store each incoming client
		//i try to create a new list for only Audio channel
		public static List<MyChannel> list = new ArrayList<MyChannel>();
		public static List<ServerAudio1> audioList = new ArrayList<ServerAudio1>();
		public static int i = 1;

		private static PrintStream Write;
		ServerSocket server;
		ServerSocket server2;
		ServerFrame sf;

		
		public server(ServerFrame MF){
	        this.sf=MF;
	        //Set the port of the server; input from mf
	        sf.Println("Server is running...");
	        
	    	//choose a setup mode
	    	
	    	int port=Integer.parseInt(sf.Port_Msg.getText().trim());
	    	int port2 = port + 1;
	    	
	  //  	int flag=Integer.parseInt(sf.Select_Msg.getText().trim());	
	    	
	        	try {
					server=new ServerSocket(port);
					 sf.Println("The port is set successfully!");
				} catch (IOException e1) {
					sf.Println("Port setup failed!");
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}    	
	        	
	        	try {
					server2=new ServerSocket(port2);
					 sf.Println("The port2 is set successfully!");
				} catch (IOException e1) {
					sf.Println("Port setup failed!");
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}   
	        	
	            new Thread(new Runnable()
	            {
	                @Override
	                public void run() 
	                {
	                	sf.Println("Waiting for client access...");
	                	 Socket Client = null;
                        while(true)
                        {
                            try 
                            {
                            	Socket client=server.accept();
                                sf.Println("Successfully connected to the client");
                            	sf.Println("this is Text chatroom" );
                                MyChannel channel=new MyChannel(client, sf);
                                list.add(channel);
                                i++;
                                channel.setSf(sf);
                                new Thread(channel).start();                            
                            } 
                            catch (IOException e) 
                            {
                            	sf.Println("Port setup failed!");
                            	sf.Println(Client.toString());
                                e.printStackTrace();
                            }
                        }
                        
	                	} 
	            	}).start(); 
	            
	            new Thread(new Runnable()
	            {
	                @Override
	                public void run() 
	                {
             //       	sf.Println("Waiting for client access...");
                        Socket Client = null;
                        while(true)
                        {
                            try 
                            {
                            	Socket client=server2.accept();
                                sf.Println("Successfully connected to the client2");
                            	sf.Println("this is Voice chatroom" );
            	        		ServerAudio1 audio=new ServerAudio1(client); 
            	            	audioList.add(audio);
            	            	i++;
                	            	//start thread
            	            	new Thread(audio).start();                             
                            } 
                            catch (IOException e) 
                            {
                            	sf.Println("Port setup failed!");
                            	sf.Println(Client.toString());
                                e.printStackTrace();
                            }
                        }
                        
	                	} 
	            	}).start(); 
	        }
}

