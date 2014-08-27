package srkl.morphological.filters.image.pgm.common;

import java.util.Objects;

public class Pixel implements Comparable<Pixel> {

    private final Coords coord;
    private final int value;

    public Pixel(Coords coord, int value) {
        this.coord = coord;
        this.value = value;
    }

    public Pixel(int x, int y, int value) {
        this(new Coords(x, y), value);
    }

    public int getX() {
        return coord.getX();
    }

    public int getY() {
        return coord.getY();
    }

    public int getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.coord);
        hash = 53 * hash + this.value;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pixel other = (Pixel) obj;
        if (!Objects.equals(this.coord, other.coord)) {
            return false;
        }
        if (this.value != other.value) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "{coord=" + coord + ", value=" + value + '}';
    }

    @Override
    public int compareTo(Pixel o) {
        return Integer.compare(value, o.value);
    }

}
