package srkl.morphological.filters.ui;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import srkl.morphological.filters.image.pgm.common.IPGMImage;

public class JPGMViewer extends javax.swing.JLabel {

    private final IPGMImage image;
    private final ImageIcon img;

    public JPGMViewer(IPGMImage image) {
        this.image = image;
        this.img = new ImageIcon(image.getAsBufferedImage());        
    }

    public IPGMImage getIPGMImage() {
        return image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        g.drawImage (img.getImage(), 0, 0, getWidth (), getHeight (), null);
    }

}
