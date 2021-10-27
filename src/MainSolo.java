import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Start the partie (for single player game)
 * 
 * 
 * Read file level
 * @author ninod
 *
 */
public class MainSolo extends JFrame {
	
	private GuiSolo gui;
	private ChampSolo ch;
	private Level fileLevel;
	
	File file;
	BufferedReader br;
	FileReader fr;
	StringBuffer sb;
	String line;
	
	FileWriter fw;
	BufferedWriter bw;

	MainSolo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setTitle("Demineur");
	    file = new File("fileLevel.txt");
	    sb = new StringBuffer(); 
	    fileLevel = readFileLevel();
		ch= new ChampSolo(fileLevel);
		ch.placeMines(fileLevel);
		gui = new GuiSolo(this);
		
		setContentPane(gui);
		pack();
		setVisible(true);
		//setSize(1600,1000);
	}
	/**
	 * 
	 * @return
	 */
	public Level readFileLevel() {
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);  
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}              
	    try {
			line = br.readLine();
			System.out.println("file :"+ line);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    switch(Integer.parseInt(line)) {
    	case 1:
    		fileLevel = Level.EASY;
    		break;
    	case 2:
    		fileLevel = Level.MEDIUM;
    		break;
    	case 3: 
    		fileLevel = Level.HARD;
    		break;
    	case 4:
    		fileLevel = Level.CUSTOM;
    		break;
    	default:
    		fileLevel = Level.EASY;
    		break;
    }
	    System.out.println("file : "+ fileLevel);
		return fileLevel;
	}
	/**
	 * 
	 */
	public void changeFileLevel(String lvl) {
		try {
			fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(lvl);
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		new MainSolo();
	}
	ChampSolo getChamp() {
		return ch;
	}
}