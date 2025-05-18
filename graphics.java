import javax.swing.*;
import java.awt.*;

public class graphics {

    private JFrame frame;

    public void createWindow(String title, int width, int height) {

        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null); // allow absolute positioning
        frame.setVisible(true);

    }

    public void createSquare(String name, int width, int height, int x, int y) {

        if (frame == null) return;

        JPanel square = new JPanel();
        square.setBackground(Color.BLACK);
        square.setBounds(x, y, width, height);
        frame.add(square);
        frame.repaint();

    }

}