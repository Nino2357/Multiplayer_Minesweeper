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

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	public void initClient() {
		gui = new GuiClient(this);
		System.out.println("dim : " + dimX + " " + dimY);
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
	public static void main(String[] args) {
		new Client();
	}
	Client(){
		this.connectToServer();
		this.initClient();
	}
}
