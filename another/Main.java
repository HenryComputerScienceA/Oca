package another;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JPanel implements MouseListener {

  int rows = 10;
  int cols = 10;

  int x1 = 100;
  int y1 = 100;

  int x2 = 300;
  int y2 = 100;

  int x3 = 100;
  int y3 = 300;

  int x4 = 300;
  int y4 = 300;


  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    // x1 y1
    g.setColor(Color.BLACK);
    g.drawRect(x1, y1, 100, 100);
    g.fillRect(x1, y1, 100, 100);

    // x2 y2
    g.setColor(Color.BLUE);
    g.drawRect(x2, y2, 100, 100);
    g.fillRect(x2, y2, 100, 100);

    // x3 y3
    g.setColor(Color.RED);
    g.drawRect(x3, y3, 100, 100);
    g.fillRect(x3, y3, 100, 100);

    // x4 y4
    g.setColor(Color.YELLOW);
    g.drawRect(x4, y4, 100, 100);
    g.fillRect(x4, y4, 100, 100);
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("window");
    frame.setSize(800, 600);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Main panel = new Main();
    
    frame.addMouseListener(panel);

    frame.add(panel);

    frame.setVisible(true);
  }

  public void mousePressed(MouseEvent e) {
    // Add your logic here if needed
    System.out.println("mouse pressed");
    System.out.println(e.getX() + " || " +  e.getY());

    int mX = e.getX();
    int mY = e.getY();

    if (mX >= 100 && mX <= 200 && mY >= 130 && mY <= 200) {
      System.out.println("pressed button 1");
    }

  }

  @Override
  public void mouseClicked(MouseEvent e) {
    // Add your logic here if needed
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    // Add your logic here if needed
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    // Add your logic here if needed
  }

  @Override
  public void mouseExited(MouseEvent e) {
    // Add your logic here if needed
  }
}
