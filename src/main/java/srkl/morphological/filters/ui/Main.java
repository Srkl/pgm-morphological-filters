package srkl.morphological.filters.ui;

import eugfc.imageio.plugins.PNMBufferedImage;
import eugfc.imageio.plugins.PNMRegistry;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import srkl.morphological.filters.image.pgm.common.IPGMImage;
import srkl.morphological.filters.image.pgm.common.PGMImageIO;
import srkl.morphological.filters.image.pgm.common.RegionCounter;
import srkl.morphological.filters.image.pgm.common.StructuringElement;
import srkl.morphological.filters.image.pgm.filters.ClosingFilter;
import srkl.morphological.filters.image.pgm.filters.DilationFilter;
import srkl.morphological.filters.image.pgm.filters.ErosionFilter;
import srkl.morphological.filters.image.pgm.filters.FilterFactory;
import srkl.morphological.filters.image.pgm.filters.GradientFilter;
import srkl.morphological.filters.image.pgm.filters.LaplacianFilter;
import srkl.morphological.filters.image.pgm.filters.OpeningFilter;
import srkl.morphological.filters.image.pgm.filters.SmoothingFilter;
import srkl.morphological.filters.image.pgm.filters.ThresholdingFilter;
import srkl.morphological.filters.image.pgm.io.StructuringElementIO;

public class Main extends javax.swing.JFrame {

    private StructuringElement se;

    public Main() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private IPGMImage openJChooserPGMFile() throws HeadlessException {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setCurrentDirectory(new File("./img").getAbsoluteFile());
        chooser.setFileFilter(new FileFilter() {

            @Override
            public boolean accept(File f) {
                return f.getName().matches(".*\\.pgm$");
            }

            @Override
            public String getDescription() {
                return "*.pgm";
            }
        });

        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.CANCEL_OPTION) {
            return null;
        }

        try {
            return PGMImageIO.read((PNMBufferedImage) ImageIO.read(chooser.getSelectedFile()));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                    "Cannot load the selected file",
                    "Loading Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

    }

    private StructuringElement openJChooserStructuringElement() throws HeadlessException {
        JFileChooser seChooser = new JFileChooser();
        seChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        seChooser.setCurrentDirectory(new File("./se").getAbsoluteFile());
        seChooser.setFileFilter(new FileFilter() {

            @Override
            public boolean accept(File f) {
                return f.getName().matches(".*\\.ee$");
            }

            @Override
            public String getDescription() {
                return "*.ee";
            }
        });

        int seResult = seChooser.showOpenDialog(this);
        if (seResult == JFileChooser.CANCEL_OPTION) {
            return null;
        }

        try {
            return StructuringElementIO.read(seChooser.getSelectedFile());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Cannot load the selected file",
                    "Loading Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

    }

    private void saveJChooserPGMFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogType(JFileChooser.SAVE_DIALOG);
        chooser.setCurrentDirectory(new File("./img").getAbsoluteFile());
        int result = chooser.showOpenDialog(this);

        if (result == JFileChooser.CANCEL_OPTION) {
            return;
        }

        if (tabViewHolder.getTabCount() > 0) {
            try {
                //            PGMImageIO.write(getSelectedImage(), chooser.getSelectedFile());
                ImageIO.write(getSelectedImage().getAsBufferedImage(), "pgm", chooser.getSelectedFile());
                JOptionPane.showMessageDialog(this, "Image Salved!");
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        tabViewHolder = new javax.swing.JTabbedPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuItemOpenFile = new javax.swing.JMenuItem();
        menuItemSaveImage = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuItemClose = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        erosionFilter = new javax.swing.JMenuItem();
        dilationFilter = new javax.swing.JMenuItem();
        smoothingFilter = new javax.swing.JMenuItem();
        gradientFilter = new javax.swing.JMenuItem();
        openingFilter = new javax.swing.JMenuItem();
        closingFilter = new javax.swing.JMenuItem();
        laplacianFilter = new javax.swing.JMenuItem();
        binarizationFilter = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        regionCounter = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setText("File");

        menuItemOpenFile.setText("Open Image");
        menuItemOpenFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemOpenFileActionPerformed(evt);
            }
        });
        jMenu1.add(menuItemOpenFile);

        menuItemSaveImage.setText("Save Image");
        menuItemSaveImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSaveImageActionPerformed(evt);
            }
        });
        jMenu1.add(menuItemSaveImage);
        jMenu1.add(jSeparator1);

        menuItemClose.setText("Exit");
        menuItemClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCloseActionPerformed(evt);
            }
        });
        jMenu1.add(menuItemClose);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Filter");

        erosionFilter.setText("Erosion");
        erosionFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                erosionFilterActionPerformed(evt);
            }
        });
        jMenu2.add(erosionFilter);

        dilationFilter.setText("Dilation");
        dilationFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dilationFilterActionPerformed(evt);
            }
        });
        jMenu2.add(dilationFilter);

        smoothingFilter.setText("Smoothing");
        smoothingFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smoothingFilterActionPerformed(evt);
            }
        });
        jMenu2.add(smoothingFilter);

        gradientFilter.setText("Gradient");
        gradientFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gradientFilterActionPerformed(evt);
            }
        });
        jMenu2.add(gradientFilter);

        openingFilter.setText("Opening");
        openingFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openingFilterActionPerformed(evt);
            }
        });
        jMenu2.add(openingFilter);

        closingFilter.setText("Closing");
        closingFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closingFilterActionPerformed(evt);
            }
        });
        jMenu2.add(closingFilter);

        laplacianFilter.setText("Laplacian");
        laplacianFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                laplacianFilterActionPerformed(evt);
            }
        });
        jMenu2.add(laplacianFilter);

        binarizationFilter.setText("Binarization");
        binarizationFilter.setToolTipText("");
        binarizationFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                binarizationFilterActionPerformed(evt);
            }
        });
        jMenu2.add(binarizationFilter);
        jMenu2.add(jSeparator2);

        regionCounter.setText("Region Count");
        regionCounter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regionCounterActionPerformed(evt);
            }
        });
        jMenu2.add(regionCounter);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabViewHolder, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabViewHolder, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuItemOpenFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemOpenFileActionPerformed
        final IPGMImage original = openJChooserPGMFile();
        if (original == null) {
            return;
        }

        se = openJChooserStructuringElement();
        if (se == null) {
            return;
        }

        renderImage("Original", original);
    }//GEN-LAST:event_menuItemOpenFileActionPerformed

    private void erosionFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_erosionFilterActionPerformed
        filterActionPerformed("Erosion", ErosionFilter.class);
    }//GEN-LAST:event_erosionFilterActionPerformed

    private void dilationFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dilationFilterActionPerformed
        filterActionPerformed("Dilation", DilationFilter.class);
    }//GEN-LAST:event_dilationFilterActionPerformed

    private void smoothingFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smoothingFilterActionPerformed
        filterActionPerformed("Smoothing", SmoothingFilter.class);
    }//GEN-LAST:event_smoothingFilterActionPerformed

    private void gradientFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gradientFilterActionPerformed
        filterActionPerformed("Gradient", GradientFilter.class);
    }//GEN-LAST:event_gradientFilterActionPerformed

    private void openingFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openingFilterActionPerformed
        filterActionPerformed("Opening", OpeningFilter.class);
    }//GEN-LAST:event_openingFilterActionPerformed

    private void closingFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closingFilterActionPerformed
        filterActionPerformed("Closing", ClosingFilter.class);
    }//GEN-LAST:event_closingFilterActionPerformed

    private void laplacianFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_laplacianFilterActionPerformed
        filterActionPerformed("Laplacian", LaplacianFilter.class);
    }//GEN-LAST:event_laplacianFilterActionPerformed

    private void menuItemCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCloseActionPerformed
        dispose();
    }//GEN-LAST:event_menuItemCloseActionPerformed

    private void binarizationFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_binarizationFilterActionPerformed
        filterActionPerformed("Binarization", ThresholdingFilter.class);
    }//GEN-LAST:event_binarizationFilterActionPerformed

    private void menuItemSaveImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSaveImageActionPerformed
        saveJChooserPGMFile();
    }//GEN-LAST:event_menuItemSaveImageActionPerformed

    private void regionCounterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regionCounterActionPerformed
        final RegionCounter c = new RegionCounter();
        final Class filter = ThresholdingFilter.class;
        final IPGMImage image = getSelectedImage();

        if (image == null) {
            JOptionPane.showMessageDialog(this,
                    "You have to load a image first",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        final int[][] data = FilterFactory.apply(filter, image, se).getData();

        int i = c.count(data);
        JOptionPane.showMessageDialog(this,
                i + " Regions Found",
                "Region Count", JOptionPane.PLAIN_MESSAGE);

    }//GEN-LAST:event_regionCounterActionPerformed

    private void filterActionPerformed(String filterName, Class filter) {
        final IPGMImage image = getSelectedImage();
        if (image == null) {
            JOptionPane.showMessageDialog(this,
                    "You have to load a image first",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        final IPGMImage processed = FilterFactory.apply(filter, image, se);
        renderImage(filterName, processed);
    }

    private IPGMImage getSelectedImage() {

        if (tabViewHolder.getSelectedComponent() instanceof JPGMViewer) {
            final JPGMViewer viewer = (JPGMViewer) tabViewHolder.getSelectedComponent();
            return (viewer == null) ? null : viewer.getIPGMImage();
        }
        return null;
    }

    private void renderImage(String name, IPGMImage image) {
        final int lastIdx = tabViewHolder.getTabCount();
        final LabelAndCloseUI lac = new LabelAndCloseUI(name);
        lac.getCloseButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                final int index = tabViewHolder.indexOfTabComponent(lac);
                tabViewHolder.removeTabAt(index);
            }
        });

        tabViewHolder.addTab(null, new JPGMViewer(image));
        tabViewHolder.setTabComponentAt(lastIdx, lac);
        tabViewHolder.setSelectedIndex(lastIdx);
        updateDimension(image);
    }

    private void updateDimension(IPGMImage image) {
        final int width = image.getWidth() + 10;
        final int height = image.getHeight() + 100;
        Dimension d = new Dimension(width, height);
        setSize(d);
        setMinimumSize(d);
        setMaximumSize(d);
        setPreferredSize(d);
        pack();
    }

    public static void main(String args[]) {

        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName()) || "GTK+".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        PNMRegistry.registerAllServicesProviders();

        java.awt.EventQueue.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem binarizationFilter;
    private javax.swing.JMenuItem closingFilter;
    private javax.swing.JMenuItem dilationFilter;
    private javax.swing.JMenuItem erosionFilter;
    private javax.swing.JMenuItem gradientFilter;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JMenuItem laplacianFilter;
    private javax.swing.JMenuItem menuItemClose;
    private javax.swing.JMenuItem menuItemOpenFile;
    private javax.swing.JMenuItem menuItemSaveImage;
    private javax.swing.JMenuItem openingFilter;
    private javax.swing.JMenuItem regionCounter;
    private javax.swing.JMenuItem smoothingFilter;
    private javax.swing.JTabbedPane tabViewHolder;
    // End of variables declaration//GEN-END:variables
}
