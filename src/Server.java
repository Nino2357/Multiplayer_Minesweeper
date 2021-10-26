import java.net.*;
import java.util.ArrayList;
import java.io.*;

/**
 * Create the server of minesweeper
 * Provide field information from class @see Champ
 * Create thread and communication with clients
 * 
 * @author ninod
 */
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
	
	
	/**
	 * Constructor
	 * Create field  @see initChamp()
	 * Create variables
	 * Wait for new players, flux in/out, create thread for each player
	 * 
	 * @throws Connexion issues with client
	 */
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
				Socket gs = gestSock.accept();
				tabSocket.add(gs);
				entree.add(new DataInputStream(tabSocket.get(nbjoueur).getInputStream()));
				sortie.add(new DataOutputStream(tabSocket.get(nbjoueur).getOutputStream()));
				score.add(0);
				tabThread.add(new Thread(this));
				numJoueur=nbjoueur;
				tabThread.get(nbjoueur).start();	
				nbjoueur ++; //Compte joueur
		}
		} catch (IOException e) {e.printStackTrace( );} 
	}
	
	/**
	 * Create communication with a plauer
	 * @param numJoueur 
	 * 		id of the player
	 * @throws communication issue
	 */
	public void initCom(int numJoueur) {
		try {
			System.out.println("Arrivée du joueur numéro : " +numJoueur);		
			sortie.get(numJoueur).writeUTF("Bienvenue. Vous etes le joueur numero :"+numJoueur);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	/**
	 * Envoie un string à un joueur
	 * @param s
	 * 		String a envoyer
	 * @param p
	 * 		id du joueur
	 * @throws erreur de communication
	 */
	public void StringtoP(String s,int p) {
		try {
			sortie.get(p).writeUTF(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Envoie un int à un joueur
	 * @param n
	 * 		int a envoyer
	 * @param p
	 * 		id du joueur
	 * @throws erreur de communication
	 */
	public void InttoP(int n,int p) {
		try {
			sortie.get(p).writeInt(n);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Recoit un int d'un joueur
	 * @param p
	 * 		id du joueur
	 * @throws erreur de communication
	 */
	public int IntfromP(int p) {
		try {			
			return entree.get(p).readInt();
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
	}
	/**
	 * Main
	 * Lance le serveur
	 * @param args
	 */
	public static void main(String[] args) {
		new Server();
	}
	/**
	 * Create a new field @see Champ
	 * Disp the field
	 */
	public void initChamp() 
	{
		champ = new Champ(Level.EASY);
		champ.placeMines(Level.EASY);
		champ.affMines();
		champ.affNb();
	}
	/*
	 * Execution du thread communicant avec un client
	 * 
	 * Communique le debut de la partie et les dimensions de la grille
	 *
	 * Attend des messages en provenance du client
	 * 
	 */
	@Override
	public void run() {
		int numThread = numJoueur;
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
					int i = IntfromP(numThread);
					int j = IntfromP(numThread);
					int discov = IntfromP(numThread);
					sendCasetoAll(i,j,discov,numThread);
					break;
				case 102:
					sendScoretoAll();
					break;
				default:
			}
			choix=-1;	
			
		}
		
	}
	/**
	 * Envoie les infos de début de partie à un joueur (taille de la grille)
	 * @param p
	 * 		id du joueur
	 */
	public void initGame(int p) {
		InttoP(champ.getDimX(),p);
		InttoP(champ.getDimY(),p);
		System.out.println("Debut partie joueur : " + p);

	}
	/**
	 * Envoie le champ complet (pour afficher le résultat final en fin de partie)
	 */
	public void sendField(int p) { 
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
	/**
	 * Send a case update to a player according to a received information
	 * @param i
	 * 		coord x champ
	 * @param j
	 * 		coord y champ
	 * @param discov
	 * 		statut reçu 
	 * 			<li>0 nothing</li>
	 * 			<li>1 open case</li>
	 * 			<li>2 mark flag</li>
	 * 			<li>3 remove flag</li>
	 * @param numThread
	 * 		id du joueur ( = numThread)
	 * 
	 * Send 
	 * 		201 start com code
	 * 		coord x and y
	 * 		num of the player who clicked on the case
	 * 			<li>2010 mark flag</li>
	 * 			<li>0 value (useless)</li>
	 * 			or
	 * 			<li>2013 remove flag</li>
	 *			<li>0 value (useless)</li>
	 *			or
	 *			<li>2011 bomb</li>
	 *			<li>0 value (useless)</li>
	 *			or
	 *			<li>2012 value case</li>
	 *			<li>value</li>
	 *
	 * Update score of the player who clicked on the case 
	 * 	+ 1 case discovered
	 * 	- 10 bomb activated 
	 * 			
	 */
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
	/**
	 * Send the score of every player to each player
	 * Code 202 (communication score)
	 * number of player
	 * Loop on player
	 * 		player num
	 * 		score of the player
	 */
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
