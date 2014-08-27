
package srkl.morphological.filters.image.pgm.common;

import java.awt.image.BufferedImage;

public interface IPGMImage extends Cloneable {
    public int getWidth();
    public int getHeight();
    public int[][] getData();
    public void setData(int i, int j, int value);
    public String getComment();
    public int getMaxGrayValue();
    public IPGMImage clone();
    public BufferedImage getAsBufferedImage();
}
