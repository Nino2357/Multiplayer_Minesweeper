import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 *
 */

public class MainGame extends JFrame {
	
	private Gui gui;
	private Champ ch;

	MainGame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setTitle("Demineur");
		ch= new Champ(Level.EASY);
		ch.placeMines(Level.EASY);
		gui = new Gui(this);

		setContentPane(gui);
		pack();
		setVisible(true);
		//setSize(1600,1000);

	}

	public static void main(String[] args) {
		new MainGame();
	}
	Champ getChamp() {
		return ch;
	}
}