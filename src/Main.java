import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author ninod
 *
 */

public class Main extends JFrame {
	
	private Gui gui;
	private Champ ch;

	Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setTitle("Demineur");
		ch= new Champ(Level.EASY);
		ch.placeMines(Level.EASY);
		gui = new Gui(this);
		// new testGrid();
		setContentPane(gui);
		pack();
		setVisible(true);
		
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
