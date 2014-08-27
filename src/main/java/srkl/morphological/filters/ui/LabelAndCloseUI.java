package srkl.morphological.filters.ui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;
import javax.swing.JLabel;

public class LabelAndCloseUI extends javax.swing.JPanel {

    public LabelAndCloseUI() {
        this("");
    }

    public LabelAndCloseUI(String name) {
        initComponents();
        label.setText(name);
        setOpaque(true);
    }

    public JLabel getJLabel() {
        return label;
    }

    public JButton getCloseButton() {
        return button;
    }

    @Override
    public void paintComponent(Graphics g) {
        // draw transparent background
        ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.0f));
        super.paintComponent(g);
        // turn on opacity
        ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        g.setColor(Color.RED);
        g.fillRect(20, 20, 500, 300);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label = new javax.swing.JLabel();
        button = new javax.swing.JButton();

        setBorder(null);
        setFocusable(false);
        setOpaque(false);

        button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/close-button.png"))); // NOI18N
        button.setBorder(null);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(button, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button;
    private javax.swing.JLabel label;
    // End of variables declaration//GEN-END:variables
}
