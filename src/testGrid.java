import java.awt.*;
import javax.swing.*;
public class testGrid
{
  testGrid()
  {
    JFrame frame = new JFrame();
    
    JButton btn1 = new JButton("A");
    JButton btn2 = new JButton("B");
    JButton btn3 = new JButton("C");
    JButton btn4 = new JButton("D");
    JButton btn5 = new JButton("E");
    JButton btn6 = new JButton("F");
    
    frame.add(btn1);
    frame.add(btn2);
    frame.add(btn3);
    frame.add(btn4);
    frame.add(btn5);
    frame.add(btn6);
    
    //définir la disposition en grille de 3 lignes et 2 colonnes
    frame.setLayout(new GridLayout(3,2));
    
    frame.setSize(300,300);
    frame.setVisible(true);
  }
}