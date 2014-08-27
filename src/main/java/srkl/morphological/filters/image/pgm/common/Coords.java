
package srkl.morphological.filters.image.pgm.common;

public class Coords {
    
    private final int x, y;

    public Coords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(x=" + x + ", y=" + y + ')';
    }
    
}
