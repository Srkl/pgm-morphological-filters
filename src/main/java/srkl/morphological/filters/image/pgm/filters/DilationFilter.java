
package srkl.morphological.filters.image.pgm.filters;

import srkl.morphological.filters.image.pgm.common.Pixel;
import srkl.morphological.filters.image.pgm.common.StructuringElement;

public class DilationFilter extends AbstractMorphoFilter {

    /**
     * Aplica filtro Dilataçao.
     * http://en.wikipedia.org/wiki/Dilation_(morphology)#Grayscale_dilation
     * @param data Matriz de pixels
     * @param i posiçao y da base
     * @param j posicao x da base
     * @param se Elemento Estruturante
     * @return Novo valor da posicao i, j da matriz de dados
     */
    @Override
    public int applyFilter(int[][] data, int i, int j, StructuringElement se) {
        int max = Integer.MIN_VALUE;
        int result = Integer.MAX_VALUE;
        final int width = data.length;
        final int height= data[0].length;
        
        for (Pixel element : se.getElements()) {
            final int x = i - element.getY();
            final int y = j - element.getX();

            if ((x >= 0 && y >= 0) && (x < width && y < height)) {
                int value = data[x][y] + element.getValue();
                if (value > max) {
                    max = value;
                    result = value - element.getValue();
                }
            }
        }
        
        return result;
    }

}
