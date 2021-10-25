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
public class CaseSolo extends JPanel implements MouseListener{
	private static final int WIDTH_CASE = 50;
	private static final int HEIGHT_CASE = 50;
	private boolean isMine;
	private int nbMinesAround;
	private boolean clicked;
	private boolean flag;
	private String text;

	/**
	 * 
	 */
	
	CaseSolo(boolean isMine, int nbMinesAround) {
		this.isMine = isMine;
		this.nbMinesAround = nbMinesAround;
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
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawString(String.valueOf(text), getWidth()/2, getHeight()/2);
		g.drawRect(0,0, getWidth(), getHeight());
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	
		
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
			 if (flag == false & clicked == false) {
				 setBackground(Color.RED);
				 flag = true;
			 }
			 else if (!clicked) {
				 setBackground(Color.GRAY);
				 flag = false;
			 }
			 
	     }
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	
}
