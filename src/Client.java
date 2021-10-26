import java.net.*;
import java.util.ArrayList;

import javax.swing.JFrame;

import java.io.*;
/**
 * Client class, create a player who join a game on a a server (default localhost:10000)
 * @author ninod
 *
 */
public class Client extends JFrame implements Runnable{
	private Thread th;
	private Socket sock;
	private DataOutputStream out;
	private DataInputStream in;
	private GuiClient gui;
	private int dimX;
	private int dimY;
	private boolean play = true;
	/**
	 * Connect to a server (default localhost:10000)
	 * Open in/out Stream
	 * @throws Connexion issue
	 */
	public void connectToServer() { 
		 try {  
		     sock = new Socket("localhost",10000); 
		     
		     out =new DataOutputStream(sock.getOutputStream()); 
		     in = new DataInputStream(sock.getInputStream()); 
		         
		 } catch (UnknownHostException e) {System.out.println("Pas de serveur");} 
		 catch (IOException e) {e.printStackTrace();} 
		 waitCom();
		 }
	/**
	 * Start a thread
	 */
	public void waitCom() {
			th = new Thread(this);
			th.start();	
	}
	/**
	 * Thread content
	 * init game
	 * listen for server communication
	 * <li>-2 error</li>
	 * <li>10 start game</li>
	 * <li>201 receive Case Value/statut</li>
	 * <li>202 receive Score</li>
	 * 
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
	     try {
			out.writeUTF("Player");
		     String msg1Server =in.readUTF();
		     System.out.println(msg1Server);
		     newGame();
		     int choix = -3;
		     while(play) {
		    	 choix = this.IntfromS();
		    	 switch(choix) {
		    	 case -2:
		    		System.out.println("error client"); 
		    	 case 10:
		     		System.out.println("debut de partie");
		    	 case 201:
		    		this.receiveCase();
		    		break;
		    	 case 202:
		    		 this.receiveScore();
		    		 break;
		     	 default:
		     		 System.out.println(choix);
		    	 }
		    	choix = -3;
		     }

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	/**
	 * Receive and analyse case value from server
	 * coord x,y
	 * player
	 * msg (code)
	 * value (0 for flag/mine, value for number)
	 */
	public void receiveCase() {
		int x = IntfromS();
		int y = IntfromS();
		int p = IntfromS();
		int msgR = IntfromS();
		int valueR = IntfromS();
		
		if (msgR==2010) { //flag
			gui.markFlag(x,y,p);
		}
		if (msgR==2013) { //remove flag
			gui.removeFlag(x,y);
		}
		if (msgR==2011) { //mine
			gui.markMine(x,y,p);
		}
		if (msgR==2012) { //number
			gui.markNum(x,y,p,valueR);
		}
	}
	/**
	 * Receive score of every player
	 * Receive number of player
	 * Receive in a loop
	 */
	public void receiveScore() {
		int numberOfPlayer= IntfromS();
		ArrayList<Integer> scoreList= new ArrayList<Integer>();
		for(int p=0;p<numberOfPlayer;p++) {
			int player = IntfromS();
			int score = IntfromS();
			scoreList.add(score);
		}
		gui.receiveScore(numberOfPlayer,scoreList);
	}
	/**
	 * Create a graphical interface with dimensions
	 */
	public void initClient() {
		gui = new GuiClient(this);
		gui.setDim(dimX, dimY);
	}
	/**
	 * Read dimension grid
	 * @throws communication issues
	 */
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
	/**
	 * Send int to the server
	 * @param n
	 * 	int to send
	 * @throws communication issue
	 */
	public void InttoS(int n) {
		try {
			out.writeInt(n);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * return int receive from Server
	 * @return server message
	 * @throws com issue
	 */
	public int IntfromS() {
		try {
			return in.readInt();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	/**
	 * Main
	 * Create a client
	 * @param args
	 */
	public static void main(String[] args) {
		new Client();
	}
	/**
	 * Leave Game
	 */
	public void quitGame() {
		this.InttoS(1);
		play=false;
	}
	/**
	 * Send information that a case was pick by the player to the server
	 * @param i
	 * 		coord x
	 * @param j
	 * 		coord y
	 * @param discov
	 * 		status
	 * 		 	<li>0 nothing</li>
	 * 			<li>1 open case</li>
	 * 			<li>2 mark flag</li>
	 * 			<li>3 remove flag</li>
	 */
	public void CasePickCli(int i, int j, int discov) {
		InttoS(101);
		InttoS(i);
		InttoS(j);
		InttoS(discov);
	}
	/**
	 * Ask score at server
	 */
	public void scoreAskCli() {
		InttoS(102);
	}
	/** 
	 * Start server com
	 */
	public void startServer() {
		this.connectToServer();
		this.initClient();
	}
	/**
	 * Constructor 
	 * Start server com
	 */
	Client(){
		this.connectToServer();
		this.initClient();
		
	}
	
}
