import java.net.*;
import java.util.ArrayList;
import java.io.*;


public class Server implements Runnable {
	
	private ArrayList<Thread> tabThread;
	private ArrayList<Socket> tabSocket;
	private ArrayList<DataInputStream> entree;
	private ArrayList<DataOutputStream> sortie;
	private ArrayList<Integer> score;
	private int nbjoueur;
	private ServerSocket gestSock;
	private int numJoueur;
	private Champ champ;
	private boolean play = true;
	
	public Server() {
		initChamp();
		tabSocket = new ArrayList<Socket>();
		tabThread = new ArrayList<Thread>();
		entree = new ArrayList<DataInputStream>();
		sortie = new ArrayList<DataOutputStream>();
		score = new ArrayList<Integer>();
		
		try {
			nbjoueur=0;
			gestSock = new ServerSocket(10000);
			while(true) {
//				System.out.println("before accept "+nbjoueur);
				Socket gs = gestSock.accept();
				tabSocket.add(gs);
				entree.add(new DataInputStream(tabSocket.get(nbjoueur).getInputStream()));
				sortie.add(new DataOutputStream(tabSocket.get(nbjoueur).getOutputStream()));
				score.add(0);
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
	
	public void StringtoP(String s,int p) {
		try {
			sortie.get(p).writeUTF(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public int IntfromP(int p) {
		try {			
			return entree.get(p).readInt();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
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
		InttoP(10,numThread); //10 debut de partie
		int choix = -1;
		
		while(play) {
			choix = IntfromP(numThread);
			switch(choix) {
				case 0:
					System.out.println("error server");
				case 1:
					System.out.println("sortie");
					play=false;
					break;
				case 101:
					System.out.println("receive 101");
					int i = IntfromP(numThread);
					int j = IntfromP(numThread);
					int discov = IntfromP(numThread);
					sendCasetoAll(i,j,discov,numThread);
					break;
				case 102:
					System.out.println("102");
					sendScoretoAll();
					break;
				default:
					//System.out.println("rien");
			}
			choix=-1;	
			
		}
		
	}
	public void initGame(int p) {
		//Envoi de la taille de la grille
		InttoP(champ.getDimX(),p);
		InttoP(champ.getDimY(),p);
		System.out.println("Debut partie joueur : " + p);
//		sendField(p);
	}
	public void sendField(int p) { //to disp result
		for(int i=0; i<champ.getDimX();i++) {
			for(int j=0;j<champ.getDimY();j++) {
				if(champ.getTabChamp()[i][j]) {
					InttoP(-1,p);
				}
				else {
					InttoP(champ.getTabChampNb()[i][j],p);
				}
			}
		}
	}
	public void sendCasetoAll(int i,int j,int discov,int numThread) {
		for(int p=0;p<nbjoueur;p++) {
			InttoP(201,p);
			InttoP(i,p);
			InttoP(j,p);
			InttoP(numThread,p);
			if(discov==2) { //if mark flag
				InttoP(2010,p);
				InttoP(0,p);
			}
			else if(discov==3) { //if remove flag
				InttoP(2013,p);
				InttoP(0,p);
			}
			else if(discov == 1) { //if open case 
				if(champ.getTabChamp()[i][j]) { //if bomb
					InttoP(2011,p);
					InttoP(0,p);
					score.set(numThread,score.get(numThread)-10); //The player touch a bomb (-10)
					
				}
				else { //if nb case
					InttoP(2012,p);
					InttoP(champ.getTabChampNb()[i][j],p); //send value
					score.set(numThread,score.get(numThread)+1);
					
				}
			}
		}
	}
	public void sendScoretoAll() {
		for(int p=0;p<nbjoueur;p++) {
			InttoP(202,p);
			InttoP(nbjoueur,p); //to configure the client loop
			for(int scoreof=0;scoreof<nbjoueur;scoreof++) {
				InttoP(scoreof,p);
				InttoP(score.get(scoreof),p);
			}
		}
	}
	
}
