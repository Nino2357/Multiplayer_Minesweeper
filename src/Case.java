import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
	private String text;

	/**
	 * 
	 */
	
	Case(boolean isMine, int nbMinesAround) {
		this.isMine = isMine;
		this.nbMinesAround = nbMinesAround;
		text = "";
		setPreferredSize(new Dimension(WIDTH_CASE, HEIGHT_CASE));
		setBackground(Color.GRAY);
		addMouseListener(this);
	
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawString(String.valueOf(text), getWidth()/2, getHeight()/2);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
            System.out.println("Left Click!");
            setBackground(Color.CYAN);
    		clicked = true;
    		if(isMine) {
    			text="X";
    		}
    		else {
    			text=String.valueOf(nbMinesAround);
    		}
    		repaint();
         }
		 if(e.getButton() == MouseEvent.BUTTON3) {
			 System.out.println("Right Click!");
			 setBackground(Color.RED);
			 
	     }
		
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	
}
