
package srkl.morphological.filters.image.pgm.filters;

import srkl.morphological.filters.image.pgm.common.StructuringElement;

/**
 *
 * @author eugf
 */
public class ThresholdingFilter extends AbstractMorphoFilter {

    boolean firstCall = true;
    private int average = 0;
    
    // Abaixo do LOW_THRESHOLD empiricamente dizemos que é preto,
    // e acima de HIGH_THRESHOLD empriricamente dizemos que é branco
    private final int LOW_THRESHOLD  = 32;
    private final int HIGH_THRESHOLD = 255 - LOW_THRESHOLD;
    
    @Override
    public int applyFilter(int[][] data, int i, int j, StructuringElement se) {
        
        // Se for a primera chamada deste metodo, é computado
        // a média de valores da região cinza da imagem
        if (firstCall) {
            firstCall = false;
            
            int height = data.length;
            int width  = data[0].length;
            
            for (int m = 0; m < height; m++) {
                for (int n = 0; n < width; n++) {
                    if (data[m][n] > LOW_THRESHOLD &&
                        data[m][n] < HIGH_THRESHOLD) {
                        average += data[m][n];
                    }
                }
            }
            
            average /= height * width;
        } else {
            // Se chegar ao fim da aplicação do filtro, deve desbloquear
            // a variavel para que possa ser utilizada numa proxima imagem
            // carregada.
            // Obviamente deveria existir dois metodos nessa interface de merda
            // algor como #beforeApply e #afterApply, mas paciencia.
            if (((i + 1) == data.length) && (j + 1) == data[0].length) {
                firstCall = true;
            }
        }
        
        if (data[i][j] <= LOW_THRESHOLD  || data[i][j] <= average) {
            return 0;
        }

        if (data[i][j] >= HIGH_THRESHOLD || data[i][j] > average) {
            return 255;
        }

        return 0; // WARNING: Este return não deve ser atingido jamais!
    }
    
}
