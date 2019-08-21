package chatroomAPP3;


public class ClientMain {
	public static void main(String[] args)
	{
		ClientFrame cf = new ClientFrame();
		cf.launchFrame();
		cf.centeredFrame(cf, Constant.WIDTH, Constant.HEIGHT);
	}
}
