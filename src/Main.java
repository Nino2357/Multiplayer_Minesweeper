import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 *
 */

public class Main extends JFrame {
	
	private Gui gui;
	private Champ ch;

	Main() {/*
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setTitle("Demineur");
		ch= new Champ(Level.EASY);
		ch.placeMines(Level.EASY);
		gui = new Gui(this);
		
		setContentPane(gui);
		pack();
		setVisible(true);
		//setSize(1600,1000);
		 * 
		 */
		System.out.println("main");
		Client Client1 = new Client();
		Client1.connectToServer();
		System.out.println("after Client");
		System.out.println("main");
		Client Client2 = new Client();
		Client2.connectToServer();
		System.out.println("after Client");
		System.out.println("main");
		Client Client3= new Client();
		Client3.connectToServer();
		System.out.println("after Client");
	}
	/**
	 * 
	 */
	public static void main(String[] args) {
		new Main();
	}
	Champ getChamp() {
		return ch;
	}
}
