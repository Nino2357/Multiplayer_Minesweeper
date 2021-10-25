import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author ninod
 *
 */
public class Case extends JPanel implements MouseListener{
	private static final int WIDTH_CASE = 50;
	private static final int HEIGHT_CASE = 50;
	private boolean isMine;
	private int nbMinesAround;
	private boolean clicked=false;
	private boolean flag;
	private String text;
	private int coordX;
	private int coordY;
	private int value;
	private GuiClient gui;
	private JLabel label;
	private boolean discover = false;
//	private Image mine;
//	private BufferedImage image;
	/**
	 * 
	 */
	
	Case(GuiClient gui, int i, int j) {
		this.gui=gui;
		coordX=i;
		coordY=j;		
//		try {
//			image = ImageIO.read(new File("./src/mine.png"));
//		} catch (IOException e) {
//			System.out.println("pb read");
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		text = "";
		flag = false;
		clicked = false;
		setPreferredSize(new Dimension(WIDTH_CASE, HEIGHT_CASE));
		setBackground(Color.GRAY);
		addMouseListener(this);
	
	}
	public boolean getFlag() {
		return this.flag;
	}
	
	public void markFlag(int p) {
		flag = true;
		System.out.println("Flag from p");
		label = new JLabel("F");
		this.add(label);
			switch(p) {
			case 0 :
				setBackground(Color.RED);
				break;
			case 1 :
				setBackground(Color.GREEN);
				break;
			case 2 :
				setBackground(Color.YELLOW);
				break;
			case 3 :
				setBackground(Color.BLUE);
				break;
			default :
				setBackground(Color.PINK);
			}
	}
	public void removeFlag() {
		flag = false;
		System.out.println("Remove flag");
		this.remove(label);
		setBackground(Color.GRAY);
	}
	
	public void markMine(int p) {
		isMine=true;
		clicked=true;
		System.out.println("Mine from p");
		label = new JLabel("X");
//	    label = new JLabel(new ImageIcon(image));
		this.add(label);
			switch(p) {
			case 0 :
				setBackground(Color.RED);
				break;
			case 1 :
				setBackground(Color.GREEN);
				break;
			case 2 :
				setBackground(Color.YELLOW);
				break;
			case 3 :
				setBackground(Color.BLUE);
				break;
			default :
				setBackground(Color.PINK);
			}
	}
	public void markNum(int p,int value) {
		isMine=false;
		clicked=true;
		System.out.println("Nume from p");
		label = new JLabel(""+ value);
		this.add(label);
			switch(p) {
			case 0 :
				setBackground(Color.RED);
				break;
			case 1 :
				setBackground(Color.GREEN);
				break;
			case 2 :
				setBackground(Color.YELLOW);
				break;
			case 3 :
				setBackground(Color.BLUE);
				break;
			default :
				setBackground(Color.PINK);
			}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
//		if (image != null) {
//			g.drawImage(image, 0, 0, null);
//		}
		g.drawString(String.valueOf(text), getWidth()/2, getHeight()/2);
		g.drawRect(0,0, getWidth(), getHeight());
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getButton() == MouseEvent.BUTTON1) {
            System.out.println("Left Click!");
    		if(clicked==false & flag==false) { //send request num
    			gui.CasePickGui(coordX, coordY, 1);//send request to server 
    			
    		}
        }
		 if(e.getButton() == MouseEvent.BUTTON3) {
			
			 System.out.println("Right Click!");
			 if (flag==false & discover==false) { //put flag
				 gui.CasePickGui(coordX, coordY, 2);//send flag to others 
			 }
			 else if (discover == false){ //remove flag
				 gui.CasePickGui(coordX, coordY, 3);
			 }
			 
	     }
		 gui.scoreAskGui(); //refresh score of players
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
