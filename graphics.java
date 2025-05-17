import javax.swing.*;

public class graphics {

    public void createWindow(String title, int width, int height) {

        JFrame frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

}