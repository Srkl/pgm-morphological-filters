

package srkl.morphological.filters.image.pgm.common;

import java.util.LinkedList;
import java.util.List;

public class StructuringElement {
    
    private final Coords base;
    private final List<Pixel> elements;

    public StructuringElement(Coords base) {
        this.elements = new LinkedList<>();
        this.base = base;
    }

    public void addElement(Pixel element) {
        elements.add(element);
    }
    
    public Coords getBase() {
        return base;
    }

    public List<Pixel> getElements() {
        return elements;
    }

    @Override
    public String toString() {
        return "StructuringElement{" + "base=" + base + ", elements=" + elements + '}';
    }
    
    
}
