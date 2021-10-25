import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 *
 */

public class MainSolo extends JFrame {
	
	private GuiSolo gui;
	private ChampSolo ch;

	MainSolo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setTitle("Demineur");
		ch= new ChampSolo(Level.EASY);
		ch.placeMines(Level.EASY);
		gui = new GuiSolo(this);
		
		setContentPane(gui);
		pack();
		setVisible(true);
		//setSize(1600,1000);
	}
	/**
	 * 
	 */
	public static void main(String[] args) {
		new MainSolo();
	}
	ChampSolo getChamp() {
		return ch;
	}
}
