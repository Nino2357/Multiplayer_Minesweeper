import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

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
	
	private MainGame main;
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
	private Level currentLevel;
	JLabel score = new JLabel();
	/**
	 * 
	 */
	Gui(MainGame mainGame) {
		this.main = mainGame;
		init();
		button();
		menu();
		initScore();
		
		
		
		
		
	    //setSize(1500,1000);
	    setVisible(true);
	}
	
	public void init() {
		currentLevel = Level.EASY;
		champ = main.getChamp();
		dimTabX = champ.getDimX();
		dimTabY = champ.getDimY();
	
		ca = new Case[dimTabX][dimTabY];
		
	    minesPanel = new JPanel();
	    minesPanel.setLayout(new GridLayout(dimTabX,dimTabY));
	    minesPanel.setVisible(true);
	    
	    minesPanel.setSize(800,500);
	    add(minesPanel, BorderLayout.CENTER);
	    
	    reset(currentLevel);
	}
	public void button() {
		butQuit = new JButton("Quit");
		add(butQuit, BorderLayout.SOUTH);
		butQuit.addActionListener(this);
		
		butReset = new JButton("Reset");
		add(butReset, BorderLayout.SOUTH);
		butReset.addActionListener(this);
	}
	public void menu() {
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
				refreshGrid();
			}
		});
		mEasy.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_E, ActionEvent.ALT_MASK));
		
		JMenuItem mMedium = new JMenuItem("MEDIUM");
		menuNiveau.add(mMedium);
		mMedium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("MEDIUM");
				refreshGrid();
				reset(Level.EASY);
			}
		});
		mMedium.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_M, ActionEvent.ALT_MASK));
		
		JMenuItem mHard = new JMenuItem("HARD");
		menuNiveau.add(mHard);
		mHard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("HARD");
				refreshGrid();
				reset(Level.EASY);
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
		
		//Reset
		JMenuItem mReset = new JMenuItem("Reset");
		menuPartie.add(mReset);
		mReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("reset");
			}
		});
		mReset.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_R, ActionEvent.ALT_MASK));
		
		//Result
		JMenuItem mResult = new JMenuItem("Result");
		menuPartie.add(mResult);
		mResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("result");
			}
		});
		mResult.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_L, ActionEvent.ALT_MASK));
		
		JMenu menuMode = new JMenu("Mode");
		menuBar.add(menuMode);
		
		//Solo
		JMenuItem mSolo = new JMenuItem("Solo");
		menuMode.add(mSolo);
		mSolo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("solo");
			}
		});
		mSolo.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_S, ActionEvent.ALT_MASK));
				
		//Mutli
		JMenuItem mMulti = new JMenuItem("Multiplayer");
		menuMode.add(mMulti);
		mMulti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("multi");
				System.exit(0);
			}
		});
		mMulti.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_C, ActionEvent.ALT_MASK));
	
		
		main.setJMenuBar(menuBar);
	}
	public void refreshGrid() {
		dimTabX = champ.getDimX();
		dimTabY = champ.getDimY();
		ca = new Case[dimTabX][dimTabY];
		minesPanel.setLayout(new GridLayout(dimTabX,dimTabY));
		minesPanel.removeAll();
		for (int i=0 ; i < dimTabX ; i++) {
			for (int j=0; j < dimTabY; j++) {
//				ca[i][j]= new Case(this,i,j);
				minesPanel.add(ca[i][j]);
			}
		 }

	    setVisible(true);
	}
	public void initScore() {
		score.setText("Score : Start" + champ.getScore());
		add(score, BorderLayout.WEST);
	}

	public void reset(Level level) {
		champ.videMines();
		champ.placeMines(level);
		champ.calculNb();
		minesPanel.removeAll();
		for (int i=0 ; i < dimTabX ; i++) {
			for (int j=0; j < dimTabY; j++) {
//				ca[i][j]= new Case(champ.getTabChamp()[i][j],champ.getTabChampNb()[i][j]);
				minesPanel.add(ca[i][j]);
			}
		 }
		currentLevel=level;
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
			reset(currentLevel);
		}/*
		if(e.getSource()== butScore) {
			System.out.println("score");
			updScore();
		}*/
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
