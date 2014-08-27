package srkl.morphological.filters.image.pgm.common;

import eugfc.imageio.plugins.PNMBufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Format Specification: http://netpbm.sourceforge.net/doc/pgm.html
 *
 * @author eugf
 */
public class PGMImageIO {

    public static P2PGMImage read(PNMBufferedImage bi) {
        int width = bi.getWidth();
        int height = bi.getHeight();
        int maxColorValue = bi.getDataTransferObject().getMaxValue();

        int[][] data = new int[height][width];

        /*
        Creates the transpose matrix of the image       
        */
        
        for (int i = 0; i < height; i++) {
            int[] buff = bi.getData().getPixels(0, i, width, 1, new int[width*4]);
            for (int j = 0, k = 0; k < buff.length; k += 4, j++) {
                data[i][j] = buff[k];
            }
        }

        return new P2PGMImage(width, height, "", maxColorValue, data);
    }

    
    @Deprecated
    
    public static P2PGMImage read(File file) {
        try (BufferedReader bf = new BufferedReader(new FileReader(file))) {

            int[][] data;
            int width = 0;
            int height = 0;
            String comment = "";
            int maxGrayValue = Byte.MAX_VALUE;

            if (bf.ready()) {
                bf.readLine(); // read magic number

                comment = bf.readLine();
                String[] dimension = bf.readLine().replace("  ", " ").split(" ");
                maxGrayValue = Integer.parseInt(bf.readLine());

                width = Integer.parseInt(dimension[0].trim());
                height = Integer.parseInt(dimension[1].trim());
            }

            int i = 0, j = 0;
            data = new int[height][width];

            while (bf.ready()) {
                String[] values = bf.readLine().trim().replace("  ", " ").split(" ");

                for (String value : values) {
                    data[i][j] = Integer.parseInt(value);
                    j = ++j % width;
                    i = (j == 0) ? ++i % height : i;
                }

            }

            return new P2PGMImage(width, height, comment, maxGrayValue, data);

        } catch (FileNotFoundException ex) {
            getLogger().log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            getLogger().log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Deprecated
    
    public static void write(IPGMImage image, File file) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {

            // A "magic number" for identifying the file type.
            bw.append(P2PGMImage.PLAIN_PGM_MAGIC_NUMBER);
            bw.newLine();

            // Comment
            bw.append(image.getComment());
            bw.newLine();

            // A width and height, formatted as ASCII characters in decimal.
            bw.append(String.valueOf(image.getWidth()))
                    .append(" ")
                    .append(String.valueOf(image.getHeight()));
            bw.newLine();

            // The maximum gray value (Maxval), again in ASCII decimal.
            // Must be less than 65536, and more than zero. 
            bw.append(String.valueOf(image.getMaxGrayValue()));
            bw.newLine();

            int characters = 0;
            final int[][] data = image.getData();

            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < image.getData()[i].length; j++) {

                    // Each pixel in the raster has white space before and after it.
                    // There must be at least one character of white space between
                    // any two pixels, but there is no maximum. 
                    final String charac = String.valueOf(data[i][j]);
                    characters += charac.length() + 1;

                    //  No line should be longer than 70 characters. 
                    if (characters >= 70) {
                        bw.newLine();
                        characters = charac.length() + 1;
                    }

                    bw.append(charac).append(" ");
                }
            }

        } catch (IOException ex) {
            getLogger().log(Level.SEVERE, null, ex);
        }
    }

    private static Logger getLogger() {
        return Logger.getLogger(PGMImageIO.class.getName());
    }

}
