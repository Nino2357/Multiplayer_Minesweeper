import java.net.*;
import java.util.ArrayList;
import java.io.*;


public class Server implements Runnable {
	
	private ArrayList<Thread> tabThread;
	private ArrayList<Socket> socket;
	private int nbjoueur;
	private ServerSocket gestSock;
	
	public Server() {
		System.out.println("in Server");
		try {
			nbjoueur=0;
			gestSock = new ServerSocket(10000);
			while(true) {
				System.out.println("before accept"+nbjoueur);;
				Socket gs = gestSock.accept();
				System.out.println(gs);
				socket.add(gs);
				tabThread.add(new Thread(this));
				System.out.println("nbjoueur");
				tabThread.get(nbjoueur).start();
				
				nbjoueur ++; 
				System.out.println("after accept");
		}
		//gestSock.close();
		} catch (IOException e) {e.printStackTrace( );} 
	}
	
	

	
	public static void main(String[] args) {
		new Server();
	}
	
	@Override
	public void run() {
		System.out.println("run");
		try {
			DataInputStream entree = new DataInputStream(socket.get(nbjoueur).getInputStream());
			DataOutputStream sortie = new DataOutputStream(socket.get(nbjoueur).getOutputStream());
			/*String nomJoueur = entree.readUTF();*/
			/*System.out.println(nomJoueur+"connected");*/
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}
	
}
;