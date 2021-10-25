import java.net.*;
import java.util.ArrayList;

import javax.swing.JFrame;

import java.io.*;

public class Client extends JFrame implements Runnable{
	private Thread th;
	private Socket sock;
	private DataOutputStream out;
	private DataInputStream in;
	private GuiClient gui;
	private int dimX;
	private int dimY;
	private boolean play = true;
	private int mode = 1;
	
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
		    		System.out.println("Receive case");
		    		this.receiveCase();
		    		break;
		    	 case 202:
		    		 System.out.println("Receive score" + choix);
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
		if (msgR==2012) {
			gui.markNum(x,y,p,valueR);
		}
	}
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
	public void initClient() {
		gui = new GuiClient(this);
		gui.setDim(dimX, dimY);
	}
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
	public void InttoS(int n) {
		try {
			out.writeInt(n);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int IntfromS() {
		try {
			return in.readInt();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	public static void main(String[] args) {
		new Client();
	}
	public void quitGame() {
		this.InttoS(1);
		play=false;
		
	}
	public void CasePickCli(int i, int j, int discov) {
		InttoS(101);
		InttoS(i);
		InttoS(j);
		InttoS(discov);
		System.out.println("Case Pick Cli");
	}
	public void scoreAskCli() {
		InttoS(102);
	}
	public void startServer() {
		this.connectToServer();
		this.initClient();
	}
	Client(){
		if(mode==0) {
			//MainGame playSolo = new MainGame();
		}
		if(mode==1) {
			this.connectToServer();
			this.initClient();
		}

	}
	
}
