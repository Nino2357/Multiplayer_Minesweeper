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
	private Champ champ;
	
	public Server() {
		initChamp();
		tabSocket = new ArrayList<Socket>();
		tabThread = new ArrayList<Thread>();
		entree = new ArrayList<DataInputStream>();
		sortie = new ArrayList<DataOutputStream>();
		try {
			nbjoueur=0;
			gestSock = new ServerSocket(10000);
			while(true) {
//				System.out.println("before accept "+nbjoueur);
				Socket gs = gestSock.accept();
				tabSocket.add(gs);
				entree.add(new DataInputStream(tabSocket.get(nbjoueur).getInputStream()));
				sortie.add(new DataOutputStream(tabSocket.get(nbjoueur).getOutputStream()));
				tabThread.add(new Thread(this));
//				System.out.println("after accept "+nbjoueur);
				numJoueur=nbjoueur;
				tabThread.get(nbjoueur).start();	
				nbjoueur ++;
		}
		
		//gestSock.close();
		} catch (IOException e) {e.printStackTrace( );} 
	}
	
	public void initCom(int numJoueur) {
		try {
			System.out.println("Arrivée du joueur numéro : " +numJoueur);
//			String nomJoueur = entree.get(numJoueur).readUTF();
//			System.out.println(nomJoueur);
		
			sortie.get(numJoueur).writeUTF("Bienvenue. Vous etes le joueur numero :"+numJoueur);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public void StringtoAll(String s) {
		for(int i=0;i<nbjoueur;i++) {
			StringtoP(s,i);
		}
	}
	
	public void StringtoP(String s,int p) {
		try {
			sortie.get(p).writeUTF(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void InttoAll(int n) {
		for(int i=0;i<nbjoueur;i++) {
			InttoP(n,i);
		}
	}
	public void InttoP(int n,int p) {
		try {
			sortie.get(p).writeInt(n);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Server();
	}
	
	public void initChamp() 
	{
		champ = new Champ(Level.EASY);
		champ.placeMines(Level.EASY);
		champ.affMines();
		champ.affNb();
	}
	
	@Override
	public void run() {
		int numThread = numJoueur;
		System.out.println("num thread : " + numThread);
		initCom(numThread);
		initGame(numThread);
		
	}
	public void initGame(int p) {
		//Envoi de la taille de la grille
		InttoP(10,p);
		InttoP(10,p);
	}
}
