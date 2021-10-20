import java.net.*;

import javax.swing.JFrame;

import java.io.*;

public class Client extends JFrame implements Runnable{
	private Thread th;
	private Socket sock;
	private DataOutputStream out;
	private DataInputStream in;
	private GuiClient gui;
	private int dimX;
	private int dimY;
	private boolean play = true;
	private int mode = 0;
	
	public void connectToServer() { 
		 try {  
		     sock = new Socket("localhost",10000); 
		     
		     out =new DataOutputStream(sock.getOutputStream()); 
		     in = new DataInputStream(sock.getInputStream()); 
		         
		 } catch (UnknownHostException e) {System.out.println("R2D2 est inconnue");} 
		 catch (IOException e) {e.printStackTrace();} 
		 waitCom();
		 }
	public void waitCom() {
			th = new Thread(this);
			th.start();
			
	
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
	     try {
			out.writeUTF("Player");
		     String msg1Server =in.readUTF();
		     System.out.println(msg1Server);
		     newGame();
		     int choix = -1;
		     while(play) {
		    	 choix = this.IntfromS();
		    	 
		    	 switch(choix) {
		    	 case 0:
		    		System.out.println("error"); 
		    	 case 10:
		     		System.out.println("debut de partie");
		     	 default:
		     		 System.out.println("rien");
		    	 }
		    	choix = -1;
		     }

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	public void initClient() {
		gui = new GuiClient(this);
		gui.setDim(dimX, dimY);
	}
	public void newGame() {
		try {
			dimX = in.readInt();
			dimY = in.readInt();
			System.out.println("dim : " + dimX + " " + dimY);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	public void InttoS(int n) {
		try {
			out.writeInt(n);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int IntfromS() {
		try {
			return in.readInt();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	public static void main(String[] args) {
		new Client();
	}
	public void quitGame() {
		this.InttoS(1);
		play=false;
		
	}
	public int getCaseValue() {
		InttoS(101);
		return IntfromS();
	}
	public void startServer() {
		this.connectToServer();
		this.initClient();
	}
	Client(){
		if(mode==0) {
			MainGame playSolo = new MainGame();
		}
		if(mode==1) {
			this.connectToServer();
			this.initClient();
		}

	}
	
}
