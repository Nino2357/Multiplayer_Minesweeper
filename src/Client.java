import java.net.*;
import java.io.*;

public class Client implements Runnable{
	private Thread th;
	private Socket sock;
	private DataOutputStream out;
	private DataInputStream in;
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
