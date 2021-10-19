import java.net.*;
import java.util.ArrayList;
import java.io.*;


public class Server implements Runnable {
	
	private ArrayList<Thread> tabThread;
	private ArrayList<Socket> tabSocket;
	private ArrayList<DataInputStream> entree;
	private ArrayList<DataOutputStream> sortie;
	private int nbjoueur;
	private ServerSocket gestSock;
	private int numJoueur;
	
	public Server() {
		System.out.println("in Server");
		tabSocket = new ArrayList<Socket>();
		tabThread = new ArrayList<Thread>();
		entree = new ArrayList<DataInputStream>();
		sortie = new ArrayList<DataOutputStream>();
		try {
			nbjoueur=0;
			gestSock = new ServerSocket(10000);
			while(true) {
				System.out.println("before accept"+nbjoueur);;
				Socket gs = gestSock.accept();
//				System.out.println(gs);
//				System.out.println(tabSocket);
				tabSocket.add(gs);
				entree.add(new DataInputStream(tabSocket.get(nbjoueur).getInputStream()));
				sortie.add(new DataOutputStream(tabSocket.get(nbjoueur).getOutputStream()));
//				System.out.println("test");
				tabThread.add(new Thread(this));
				System.out.println("after accept"+nbjoueur);
				numJoueur=nbjoueur;
				tabThread.get(nbjoueur).start();
				
				nbjoueur ++;
		}
		//gestSock.close();
		} catch (IOException e) {e.printStackTrace( );} 
	}
	
	public void initCom(int numJoueur) {
		try {
			System.out.println(numJoueur);
			String nomJoueur = entree.get(numJoueur).readUTF();
			System.out.println(nomJoueur);
			sortie.get(numJoueur).writeUTF("bienvenue"+nomJoueur+"vous etes le joueur num"+numJoueur);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	
	public static void main(String[] args) {
		new Server();
	}
	
	@Override
	public void run() {

		System.out.println("run"+numJoueur);
		System.out.println(numJoueur); 
		initCom(numJoueur);
		
	}
	
}
;