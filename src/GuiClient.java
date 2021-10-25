import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class GuiClient extends JPanel implements ActionListener {
	
	private JButton butQuit;
	private JButton butReset;
	private int dimTabX = 10;
	private int dimTabY = 10;
	private Case ca[][];
	private Client cli;
	private JPanel minesPanel;
	private JMenuItem mQuitter;
	private JPanel scorePanel;
	/**
	 * 
	 */
	GuiClient(Client cli) {
		this.cli = cli;
		button();
		init();
		menu();
	    setVisible(true);
	}
	
	public void markFlag(int x,int y, int p) {
		ca[x][y].markFlag(p);
		refreshGrid();
	}
	public void removeFlag(int x, int y) {
		ca[x][y].removeFlag();
		refreshGrid();
	}
	
	public void markMine(int x, int y, int p) {
		ca[x][y].markMine(p);
		refreshGrid();
		
	}
	public void markNum(int x, int y, int p, int value) {
		ca[x][y].markNum(p,value);
		refreshGrid();
	}
	public void receiveScore(int numberOfPlayer,ArrayList<Integer> scoreList) {
		scorePanel.removeAll();
		scorePanel.add(butQuit);
		scorePanel.add(butReset);
		scorePanel.add(new JLabel("Score :"));
		for(int scoreOf=0;scoreOf<numberOfPlayer;scoreOf++) {
			scorePanel.add(new JLabel("Score of player "+scoreOf+" : "+scoreList.get(scoreOf)/numberOfPlayer)); //to balance server score
			System.out.println("Score of player "+scoreOf+" : "+scoreList.get(scoreOf)/numberOfPlayer);
		}
		System.out.println("Receive Score");
//		refreshGrid();
	}
	
	public void setDim(int dimX,int dimY)
	{
		dimTabX = dimX;
		dimTabY = dimY;
	}
	
	public void init() {
		this.setLayout(new GridLayout(2,1));
	    minesPanel = new JPanel();
	    minesPanel.setLayout(new GridLayout(dimTabX,dimTabY));
	    minesPanel.setVisible(true);
	    minesPanel.setSize(800,500);
	    ca = new Case[dimTabX][dimTabY];
	    add(minesPanel); 
	    scorePanel = new JPanel();
	    scorePanel.setLayout(new GridLayout(8,1));
	    scorePanel.setVisible(true);
	    scorePanel.setSize(500,300);
	    add(scorePanel);
	   
	    
		for (int i=0 ; i < dimTabX ; i++) {
			for (int j=0; j < dimTabY; j++) {
				ca[i][j]= new Case(this,i,j);
				minesPanel.add(ca[i][j]);
			}
		 }
	    scorePanel.add(butQuit);
	    scorePanel.add(butReset);
	    scorePanel.add(new JLabel("Score :"));
	    
	    
	    refreshGrid();
	}
	
	public void button() {
		butQuit = new JButton("Quit");
		butQuit.addActionListener(this);
		
		butReset = new JButton("Reset");
		
		butReset.addActionListener(this);
	    cli.setContentPane(this);
	    cli.pack();
	    cli.setVisible(true);
	}

	public void newGrid() {
		ca = new Case[dimTabX][dimTabY];
		minesPanel.setLayout(new GridLayout(dimTabX,dimTabY));
		minesPanel.removeAll();
	    setVisible(true);
	}	
	public void CasePickGui(int i, int j, int discov) {
		cli.CasePickCli(i,j,discov);
		System.out.println("CasePickGui");
	}
	public void scoreAskGui() {
		cli.scoreAskCli();
	}
	public void refreshGrid() {
	    cli.setContentPane(this);
	    cli.pack();
	    cli.setVisible(true);
	}

	public void reset() {
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
				reset();
			}
		});
		mMedium.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_M, ActionEvent.ALT_MASK));
		
		JMenuItem mHard = new JMenuItem("HARD");
		menuNiveau.add(mHard);
		mHard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("HARD");
				refreshGrid();
				reset();
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
				
			}
		});
		mMulti.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_C, ActionEvent.ALT_MASK));
	
		
		cli.setJMenuBar(menuBar);
	    cli.setContentPane(this);
	    cli.pack();
	    cli.setVisible(true);
	}




}


