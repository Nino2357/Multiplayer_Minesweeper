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

public class Gui extends JPanel implements ActionListener {
	
	private Main main;
	private JButton butQuit;
	private JButton butReset;
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
		
		//Score
		JLabel score = new JLabel();
		score.setText("Score");
		add(score, BorderLayout.SOUTH);

		// create the panel for mines
		champ = main.getChamp();
		dimTabX = champ.getDimX();
		dimTabY = champ.getDimY();
		
		ca = new Case[dimTabX][dimTabY];
		
	    minesPanel = new JPanel();
	    minesPanel.setLayout(new GridLayout(dimTabX,dimTabY));
	    
		for (int i=0 ; i < dimTabX ; i++) {
			for (int j=0; j < dimTabY; j++) {
				ca[i][j]= new Case(champ.getTabChamp()[i][j],champ.getTabChampNb()[i][j]);
				minesPanel.add(ca[i][j]);
				
			}
		 }
	
		//Dispp Panel
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
		
		//Quitter
		JMenuItem mQuitter = new JMenuItem("Quitter",KeyEvent.VK_Q);
		menuPartie.add(mQuitter);
		mQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("quit");
				System.exit(0);
			}
		});
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
		main.setJMenuBar(menuBar);
		
	    setSize(1000,700);
	    setVisible(true);
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
	public void Reset() {
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
		if(e.getSource() == mQuitter) {
			System.out.println("quit");
			System.exit(0);
		}
		if(e.getSource() == butQuit || e.getSource() == mQuitter) {
			System.out.println("quit");
			System.exit(0);
		}
		if(e.getSource() == butReset) {
			System.out.println("reset");
			Reset();
		}
	}
}
