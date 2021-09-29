import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class Gui extends JPanel implements ActionListener {
	
	private Main main;
	private JButton butQuit;
	private JButton butReset;
	private JMenuItem mEasy;
	private JMenuItem mMedium;
	private JMenuItem mHard;
	private int dimTabX;
	private int dimTabY;
	private Champ champ;
	private JPanel minesPanel;
	private Case ca[][];
	private JMenuItem mQuitter;
	/**
	 * 
	 */
	Gui(Main main) {
		this.main = main;
		

		// create the panel for mines
		champ = main.getChamp();
		dimTabX = champ.getDimX();
		dimTabY = champ.getDimY();
	
		
		ca = new Case[dimTabX][dimTabY];
		
	    minesPanel = new JPanel();
	    minesPanel.setLayout(new GridLayout(dimTabX,dimTabY));
	    
	    reset();
	 
		//Disp Panel
	    minesPanel.setVisible(true);
	    minesPanel.setSize(800,500);
	    add(minesPanel, BorderLayout.CENTER);
	    
	    //Buttons
	    butQuit = new JButton("Quit");
		add(butQuit, BorderLayout.SOUTH);
		butQuit.addActionListener(this);
		
		butReset = new JButton("Reset");
		add(butReset, BorderLayout.SOUTH);
		butReset.addActionListener(this);
				
		
		
		//Menu
		JMenuBar menuBar = new JMenuBar();
		
		JMenu menuPartie = new JMenu("Partie");
		menuBar.add(menuPartie);
		
		JMenu menuNiveau = new JMenu("Niveau");
		menuBar.add(menuNiveau);
		
		JMenuItem mEasy = new JMenuItem("EASY");
		menuNiveau.add(mEasy);
		mEasy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("EASY");
				champ.changeLvl(Level.EASY);
			}
		});
		mEasy.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_E, ActionEvent.ALT_MASK));
		
		JMenuItem mMedium = new JMenuItem("MEDIUM");
		menuNiveau.add(mMedium);
		mMedium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("MEDIUM");
				System.out.print("Test0");
				champ.changeLvl(Level.MEDIUM);
				System.out.print("Test1");
				dimTabX = champ.getDimX();
				dimTabY = champ.getDimY();
				minesPanel.setLayout(new GridLayout(dimTabX,dimTabY));
				System.out.print("testA");
				minesPanel.removeAll();
				for (int i=0 ; i < dimTabX ; i++) {
					for (int j=0; j < dimTabY; j++) {
						ca[i][j]= new Case(champ.getTabChamp()[i][j],champ.getTabChampNb()[i][j]);
						System.out.print("i");
						minesPanel.add(ca[i][j]);
					}
				 }
			    setVisible(true);
				
			    
			}
		});
		mMedium.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_M, ActionEvent.ALT_MASK));
		
		JMenuItem mHard = new JMenuItem("HARD");
		menuNiveau.add(mHard);
		mHard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("HARD");
				champ.changeLvl(Level.HARD);
				
			}
		});
		mHard.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_H, ActionEvent.ALT_MASK));
		
		//Quitter
		JMenuItem mQuitter = new JMenuItem("Quitter");
		menuPartie.add(mQuitter);
		mQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("quit");
				System.exit(0);
			}
		});
		mQuitter.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_Q, ActionEvent.ALT_MASK));
		
		
		
		//Score
		JLabel score = new JLabel();
		score.setText("Score :" + champ.getScore());
		add(score, BorderLayout.WEST);
		
		main.setJMenuBar(menuBar);
		
		
	    setSize(1000,700);
	    setVisible(true);
	}
	

	public void reset() {
		champ.videMines();
		champ.placeMines(Level.EASY);
		minesPanel.removeAll();
		for (int i=0 ; i < dimTabX ; i++) {
			for (int j=0; j < dimTabY; j++) {
				ca[i][j]= new Case(champ.getTabChamp()[i][j],champ.getTabChampNb()[i][j]);
				minesPanel.add(ca[i][j]);
			}
		 }
	    main.setContentPane(this);
	    main.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == butQuit || e.getSource() == mQuitter) {
			System.out.println("quit");
			System.exit(0);
		}
		if(e.getSource() == butReset) {
			System.out.println("reset");
			reset();
		}
	}
	public void comptFlag() {
		champ.setScore(0);
		for (int i=0 ; i < dimTabX ; i++) {
			for (int j=0; j < dimTabY; j++) {
				if (ca[i][j].getFlag() == true);
					champ.add1Flag();
			}
		 }
		
	}
}




/*
public void Reset2() {
	champ.videMines();
	champ.placeMines(Level.EASY);
	minesPanel.removeAll();
	for (int i=0 ; i < dimTabX ; i++) {
		for (int j=0; j < dimTabY; j++) {
			if (champ.getTabChamp()[i][j]==true) {
				minesPanel.add(new JLabel((Icon) new ImageIcon(new ImageIcon(getClass().getResource("/mine.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH))));
			}
			else {
				minesPanel.add(new JLabel((Icon) new ImageIcon(new ImageIcon(getClass().getResource("/icon.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH))));
			}		
		}
	 }
    main.setContentPane(this);
    main.setVisible(true);
}*/


/*
//NivEasy
JMenuItem mNivEasy = new JMenuItem("Easy",KeyEvent.VK_E);
menuPartie.add(mQuitter);
mQuitter.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		System.out.println("quit");
		System.exit(0);
		}
});*/
