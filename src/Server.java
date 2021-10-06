import java.net.*;
import java.io.*;


public class Server implements Runnable {
	
	public Server() {
		System.out.println("in Server");
		try {
			ServerSocket gestSock = new ServerSocket(10000);
			System.out.println("before accept");
			Socket socket= gestSock.accept();
			System.out.println("after accept");
			
			DataInputStream entree = new DataInputStream(socket.getInputStream()); 
		    DataOutputStream sortie = new DataOutputStream(socket.getOutputStream()); 
		
		    // lecture d�une donn�e  
		    String nomJoueur = entree.readUTF() ;  
		    System.out.println(nomJoueur+"connected");
		     
		    // envoi d �une donn�e : 0 par exemple 
		    sortie.writeInt(0);  
		    // un peu de m�nage 
		    sortie.close() ; 
		  	entree.close() ; 
		    socket.close();  
		    gestSock.close() ; 
		 } catch (IOException e) {e.printStackTrace( );} 
	}
	
	

	
	public static void main(String[] args) {
		new Server();
	}
	
	
	@Override
	public void run() {
		System.out.println("run");
		// TODO Auto-generated method stub
		
	}
	
}
;