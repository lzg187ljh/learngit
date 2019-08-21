package chatroomAPP3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ServerMain extends ServerFrame
{
	
	public static void main(String[] args)
	{
		ServerFrame sf = new ServerFrame();
		sf.launchFrame();
		sf.centeredFrame(sf, Constant.WIDTH, Constant.HEIGHT);
	}
}
