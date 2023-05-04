package views;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel {

    Image img;

    public BackgroundPanel(String path) {
        try {
            img = ImageIO.read(new File(path));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draws the img to the BackgroundPanel.
        if (img != null) {
            g.drawImage(img, 0, 0, null);
        }
    }
    
}
