package other;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class window {

  static Color black = new Color(0, 0, 0);
  static Color darkGreen = new Color(0, 100, 0);
  static Color white = new Color(255, 255, 255);


  public static void main(String[] args) {
    JFrame frame = new JFrame("window");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(800, 600);

    Font defaultFont = new Font("Ariel", Font.BOLD,  20);

    /* ONE  */

    JTextArea one = new JTextArea();
    one.setBackground(black);
    one.setForeground(white);
    one.setCaretColor(white);
    one.setFont(defaultFont);

    one.setBorder(BorderFactory.createLineBorder(black, 20));
    

    JPanel onePanel = new JPanel(new BorderLayout());
    onePanel.setBorder(BorderFactory.createLineBorder(darkGreen, 10));

    onePanel.add(one);

    /* ONE  */


    /* BUTTON  */

    JButton submit = new JButton("submit");

    JPanel bP = new JPanel(new BorderLayout());
    bP.add(submit);

    /* BUTTON  */

    /* TWO  */

    JTextArea two = new JTextArea();
    two.setBackground(Color.BLACK);
    two.setBackground(black);
    two.setForeground(white);
    two.setCaretColor(white);
    two.setFont(defaultFont);

    two.setBorder(BorderFactory.createLineBorder(black, 20));

    JPanel twoPanel = new JPanel(new BorderLayout());
    twoPanel.setBorder(BorderFactory.createLineBorder(darkGreen, 10));

    twoPanel.add(two);

    /* TWO  */




    /* READER */

    

    submit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String line = "";
        String input = "";

        line = two.getText();

        System.out.println(line);

        String[] i = line.split(" "); // an array

        if (line.startsWith("test ")) {
          System.out.println("recognized word");
        } 
        
        for (String in : i) {
          System.out.println("input: " + in);

          if (in.startsWith("test")) {
            System.out.println("recognized word also");

            if (i[1].equals("new")) {
              System.out.println("recognized last part");
            }

          } 

        }
        

      }
    });

    



    /* READER */











    JSplitPane pane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, twoPanel, bP);
    pane.setResizeWeight(0.5);

    frame.add(pane);

    frame.setVisible(true);

  }
}
