package chatroomAPP3;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.net.Socket;
import java.util.List;

public class ClientFrame extends JFrame
{
	public JButton Connect;
	public JButton Send;
	public JButton Ok;
	public JButton Confirm;
	public JTextField Flag_Msg;
    public JTextField Say_Msg;
    public JTextField nick_name;
    public JTextField Server_IP; 
    public JTextField Server_Port; 
    public JPanel First_panel;
    public JPanel Top_panel;
    public JPanel Mid_panel;
    public JPanel Panel_up;
    public JPanel Second_panel;
    public JPanel Panel_down;
    public JTextArea Text_show;
    public client client;
    public String username;
    
    
    
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void launchFrame()
	{
		 nick_name =new JTextField(10);
		 Ok = new JButton("Ok");
		First_panel=new JPanel();
		Mid_panel=new JPanel();
		Top_panel=new JPanel();
		
		
		
		Mid_panel.setLayout(new FlowLayout());   
		Mid_panel.add(new JLabel("Nick_Name : "));
		Mid_panel.add(nick_name);
		Mid_panel.add(Ok); 	
		Mid_panel.setEnabled(true);
		First_panel.add(Top_panel, BorderLayout.NORTH);
        First_panel.add(Mid_panel, BorderLayout.CENTER);
        this.add(First_panel);
        this.setTitle("client");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);	
        
        Ok.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            	First_panel.setVisible(false); 
            	First_panel.setEnabled(false);
            	setUsername(nick_name.getText().trim());               
                SecondPanel();
            }
        });
       
             	
	}
	
	public void SecondPanel()
	{
	      Server_IP=new JTextField(10);
	        Server_Port=new JTextField(5);
	        Say_Msg=new JTextField(25);	       
	        
			Connect=new JButton("Connect");
	        Send=new JButton("Send"); 
	        
	        Text_show=new JTextArea();
	        JScrollPane ScrollPane=new JScrollPane();
	        ScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	        ScrollPane.setViewportView(Text_show);
	        
	        Panel_up=new JPanel();
	        Panel_up.setLayout(new GridBagLayout());
	     //   Panel_up.add(new JLabel("Flag"));
	      //  Panel_up.add(Flag_Msg);
	     //   Panel_up.add(Confirm);
	        Panel_up.add(new JLabel("Server IP"));
	        Panel_up.add(Server_IP);
	        Panel_up.add(new JLabel("Server Port"));
	        Panel_up.add(Server_Port);
	        Panel_up.add(Connect);

	        
	        Panel_down=new JPanel();
	        Panel_down.setLayout(new FlowLayout());   
	  //      Panel_down.add(new JLabel("nick_name"));
	   //     Panel_down.add(nick_name);
	    //    Panel_down.add(Ok);
	        Panel_down.add(new JLabel("Say_Msg"));
	        Panel_down.add(Say_Msg);
	        Panel_down.add(Send);
	       
	        
	        
	        Second_panel=new JPanel();
	        Second_panel.setLayout(new BorderLayout());
	        Second_panel.add(Panel_up, BorderLayout.NORTH);
	        Second_panel.add(ScrollPane,BorderLayout.CENTER);
	        Second_panel.add(Panel_down,BorderLayout.SOUTH);
            Second_panel.setVisible(true);
            Second_panel.setEnabled(true);
	        
			this.setTitle("client");
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setSize(Constant.WIDTH, Constant.HEIGHT);			
			this.add(Second_panel);
			setVisible(true);	
				
			Println("Welcome!\n"+ getUsername() +" you can start chating now");
			
	        Connect.addActionListener(new ActionListener(){
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                client = new client(ClientFrame.this);
	            }
	        });

	        Send.addActionListener(new ActionListener(){
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	client.send.setUsername(getUsername());
	                client.send.send(getUsername() +" : "+ Say_Msg.getText());            
	                Say_Msg.setSelectedTextColor(Color.RED);
	                Println2("                                                                                  " + client.send.getUsername() +" : "+ Say_Msg.getText());
	                Say_Msg.setText("");
	            }
	        });
	        	        
	        /*
	        Confirm.addActionListener(new ActionListener(){
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	setFlag(Integer.parseInt(Flag_Msg.getText().trim()));
	            	Println("Flag : "+ flag);
	            }
	        });
	        */
	        
			new PaintThread().start();	
			addWindowListener(new WindowAdapter()
			{
				public void windowClosing(WindowEvent e)
				{
					System.exit(0);
				}
			});
	}
	/*
	public void paint(Graphics g)
	{
	
	}
    
	public void printInfo(Graphics g, String str, int size, int x, int y)
	{
		Color c = g.getColor();
		g.setColor(Color.RED);
		Font f = new Font("Arial", Font.BOLD, size);
		g.setFont(f);
		g.drawString(str, x, y);
		g.setColor(c);
	}
	*/
    public void Println(String s)
    {
        if(s!=null){
            this.Text_show.setText(this.Text_show.getText()+s+"\n");
            this.Text_show.setForeground(Color.BLUE);
            Font f = new Font("Serif",0,25);
            this.Text_show.setFont(f);
        }
    }
    
    
    public void Println2(String s)
    {
        if(s!=null){
            this.Text_show.setText(this.Text_show.getText()+s+"\n");
            Font f = new Font("Serif",0,25);
            this.Text_show.setFont(f);
        }
    }
    
	//Eliminate flicker with double buffering
	private Image offScreenImage = null;
	public void update(Graphics g)
	{
		if(offScreenImage == null)
		{
			offScreenImage = this.createImage(Constant.WIDTH, Constant.HEIGHT);
		}
		
		Graphics gOff = offScreenImage.getGraphics();
		paint(gOff);
		g.drawImage(offScreenImage, 0, 0, null);
	}
	
	
	public void centeredFrame(Frame f, int width, int height)
	{
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		int x = (int)d.getWidth();
		int y = (int)d.getHeight();
		f.setBounds((x-width)/2, (y-height)/2, width, height);
	}
	
	class PaintThread extends Thread
	{
		public void run()
		{
			while(true)
			{
				repaint();
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
