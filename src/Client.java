import java.net.*;
import java.io.*;

public class Client {
	public void connectToServer() { 
		 try {
		     Socket sock = new Socket("localhost",10000); 
		     
		     DataOutputStream out =new DataOutputStream(sock.getOutputStream()); 
		     DataInputStream in = new DataInputStream(sock.getInputStream()); 
		     
		     out.writeUTF("Gros Bill"); 
		     
		     int numJoueur = in.readInt(); 
		     System.out.println("Joueur n°:"+numJoueur); 
		     in.close(); 
		     out.close();  
		     sock.close() ;  
		 } catch (UnknownHostException e) {System.out.println("R2D2 est inconnue");} 
		 catch (IOException e) {e.printStackTrace();} 
		 }
	
}
