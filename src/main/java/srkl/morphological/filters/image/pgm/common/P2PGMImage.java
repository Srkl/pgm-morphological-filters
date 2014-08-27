package srkl.morphological.filters.image.pgm.common;

import java.awt.image.BufferedImage;

public class P2PGMImage implements IPGMImage {

    public static final String PLAIN_PGM_MAGIC_NUMBER = "P2";

    private int width;
    private int height;
    private int[][] data;
    private String comment;
    private int maxGrayValue;

    public P2PGMImage(int width, int height, String comment,
            int maxGrayValue, int[][] data) {
        this.width = width;
        this.height = height;
        this.data = data;
        this.comment = comment;
        this.maxGrayValue = maxGrayValue;
    }

    private P2PGMImage(P2PGMImage image) {
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.data = clone(image.data);
        this.comment = image.getComment();
        this.maxGrayValue = image.getMaxGrayValue();
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public String getComment() {
        return comment;
    }

    @Override
    public int getMaxGrayValue() {
        return maxGrayValue;
    }

    @Override
    public int[][] getData() {
        return data;
    }

    @Override
    public void setData(int i, int j, int value) {
        data[i][j] = value;
    }

    private int[][] clone(int[][] data) {
        int[][] newData = new int[data.length][data[0].length];
        for (int i = 0; i < data.length; i++) {
            System.arraycopy(data[i], 0, newData[i], 0, data[i].length);
        }
        return newData;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        String fmt = "P2\n%s\n%d %d\n%d\n";
        builder.append(String.format(fmt, comment, width, height, maxGrayValue));

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                builder.append(String.format("%03d\t", data[i][j]));
            }
            builder.append("\n");
        }

        return builder.toString();
    }

    @Override
    public IPGMImage clone() {
        return new P2PGMImage(this);
    }

    //http://www.cs.olemiss.edu/~jxue/teaching/csci112_S11/notes/hw1/PgmImage.java
    @Override
    public BufferedImage getAsBufferedImage() {
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int p = data[i][j];
                bufferedImage.setRGB(j, i, ((255 << 24) | (p << 16) | (p << 8) | p));
            }
        }
        return bufferedImage;
    }

}
