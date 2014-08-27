
package srkl.morphological.filters.image.pgm.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import srkl.morphological.filters.image.pgm.common.Coords;
import srkl.morphological.filters.image.pgm.common.Pixel;
import srkl.morphological.filters.image.pgm.common.StructuringElement;

/**
 *
 * @author eugf
 */
public class StructuringElementIO {

    public static StructuringElement read(File file) {
        try (BufferedReader bf = new BufferedReader(new FileReader(file))) {
            
            bf.readLine(); // TODO: testar sanidade e compara com EE
            bf.readLine(); // foda se a dimensao
            String[] base = bf.readLine().trim().split(" ");
            final int y = Integer.parseInt(base[1]);
            final int x = Integer.parseInt(base[0]);
            StructuringElement se = new StructuringElement(new Coords(x, y));
            
            for (int j = 0; bf.ready(); j++) {
                
                String[] values = bf.readLine().split(" ");
                
                for (int i = 0; i < values.length; i++) {
                    
                    if (values[i].equals("*")) continue;
                    
                    final Coords coords = new Coords(x - i, y - j);
                    final int value = Byte.parseByte(values[i]);
                    final Pixel element = new Pixel(coords, value);
                    
                    se.addElement(element);
                }
            }
            
            return se;
            
        } catch (FileNotFoundException ex) {
            getLogger().log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            getLogger().log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static Logger getLogger() {
        return Logger.getLogger(StructuringElementIO.class.getName());
    }
    
}
