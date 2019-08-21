package chatroomAPP3;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.net.Socket;
import java.util.List;

public class ServerFrame extends JFrame
{
	public JButton Start;
	public JButton OK;
    public JTextField Port_Msg;
    public JTextField Select_Msg;
    public JPanel Panel_up;
    public JPanel Panel_down;
    public JPanel Panel;
    public JTextArea Text_show;
    public server server;

	
	public void launchFrame()
	{
		Start=new JButton("Start");
    //    OK=new JButton("OK");
        
        Port_Msg=new JTextField(10);
     //   Select_Msg=new JTextField(10);
        
        Text_show=new JTextArea();
        JScrollPane ScrollPane=new JScrollPane();
        ScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        ScrollPane.setViewportView(Text_show);
        
        Panel_up=new JPanel();
        Panel_up.setLayout(new FlowLayout());
        Panel_up.add(new JLabel("Port"));
        Panel_up.add(Port_Msg);
        Panel_up.add(Start);
        
        /*
        Panel_down=new JPanel();
        Panel_down.setLayout(new FlowLayout());
        Panel_down.add(new JLabel("Flag"));
        Panel_down.add(Select_Msg);
        Panel_down.add(OK);   
        */
        
        Panel=new JPanel();
        Panel.setLayout(new BorderLayout());
        Panel.add(Panel_up, BorderLayout.NORTH);
        Panel.add(ScrollPane,BorderLayout.CENTER);
    //    Panel.add(Panel_down, BorderLayout.S);
        
		this.setTitle("server");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Constant.WIDTH, Constant.HEIGHT);
		setLocation(100, 100);
		this.add(Panel);
		setVisible(true);	
			
        Start.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                server=new server(ServerFrame.this);
            }
        });
           
        Println("1:Text chatroom");
        Println("2:Voice chatroom");
        /*
        OK.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {    	
            	setFlag(Integer.parseInt(Select_Msg.getText().trim()));
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
    public void Println(String s)
    {
        if(s!=null){
            this.Text_show.setText(this.Text_show.getText()+s+"\n");
            this.Text_show.setForeground(Color.BLUE);
            Font f = new Font("Serif",0,25);
            this.Text_show.setFont(f);
       //    System.out.println(s + "\n");
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
