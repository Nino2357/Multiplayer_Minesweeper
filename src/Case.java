import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
	private boolean clicked;
	private boolean flag;
	private String text;
	private int coordX;
	private int coordY;
	private int value;
	private GuiClient gui;
	private JLabel label;

	/**
	 * 
	 */
	
	Case(GuiClient gui, int i, int j) {
		this.gui=gui;
		coordX=i;
		coordY=j;		
		
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
		label = new JLabel("F - " + p);
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
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawString(String.valueOf(text), getWidth()/2, getHeight()/2);
		g.drawRect(0,0, getWidth(), getHeight());
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getButton() == MouseEvent.BUTTON1) {
			
            System.out.println("Left Click!");
    		clicked = true;
    		if(isMine & !flag) {
    			text="X";
    			setBackground(Color.CYAN);
    		}
    		else if (!flag) {
    			text=String.valueOf(nbMinesAround);
    			setBackground(Color.CYAN);
    		}
    		repaint();
         }
		 if(e.getButton() == MouseEvent.BUTTON3) {
			
			 System.out.println("Right Click!");
			 if (flag == false) { //put flag
				 gui.CasePickGui(coordX, coordY, 2);//send flag to others 
			 }
			 else { //remove flag
				 gui.CasePickGui(coordX, coordY, 3);
				 flag = false;
			 }
			 
	     }
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
