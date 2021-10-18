import java.net.*;
import java.util.ArrayList;
import java.io.*;


public class Server implements Runnable {
	
	private ArrayList<Thread> tabThread;
	private ArrayList<Socket> tabSocket;
	private int nbjoueur;
	private ServerSocket gestSock;
	
	public Server() {
		System.out.println("in Server");
		tabSocket = new ArrayList<Socket>();
		tabThread = new ArrayList<Thread>();
		try {
			nbjoueur=0;
			gestSock = new ServerSocket(10000);
			while(true) {
				System.out.println("before accept"+nbjoueur);;
				Socket gs = gestSock.accept();
//				System.out.println(gs);
//				System.out.println(tabSocket);
				tabSocket.add(gs);
//				System.out.println("test");
				tabThread.add(new Thread(this));
				System.out.println("before accept"+nbjoueur);
				tabThread.get(nbjoueur).start();
				
				nbjoueur ++; 
//				System.out.println("after accept");
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
			DataInputStream entree = new DataInputStream(tabSocket.get(nbjoueur).getInputStream());
			DataOutputStream sortie = new DataOutputStream(tabSocket.get(nbjoueur).getOutputStream());
			/*String nomJoueur = entree.readUTF();*/
			/*System.out.println(nomJoueur+"connected");*/
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}
	
}
;