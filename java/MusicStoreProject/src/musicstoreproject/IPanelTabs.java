
package musicstoreproject;

/**
 *
 * @author Leo Dubovyi, Valerii Doroshenko
 * Vanier College
 * System Analysis
 * Course Project
 * @Project  MusicStore @Class IPanel
 */

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

public class IPanelTabs extends JPanel{

    private Image bgImage;

    public IPanelTabs(String img){
        try {
            this.bgImage = ImageIO.read(new File(img));
        } catch (IOException ex) {
            Logger.getLogger(IPanelTabs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.lightGray);
        g.setFont(new Font("Verdana", Font.ITALIC,32));
        g.drawImage(bgImage, 0, 0, this);
        g.drawString("Welcome to the music store", 20,505);


    }
}
