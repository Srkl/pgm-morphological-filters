
package srkl.morphological.filters.image.pgm.filters;

import srkl.morphological.filters.image.pgm.common.StructuringElement;

public class ClosingFilter extends AbstractMorphoFilter {

    private final AbstractMorphoFilter dilationFilter;
    private final AbstractMorphoFilter erosionFilter;

    public ClosingFilter() {
        dilationFilter = new DilationFilter();
        erosionFilter  = new ErosionFilter();
    }
    
    /**
     * Aplica filtro de abertura.
     * http://ee.lamar.edu/gleb/dip/10-3%20-%20Morphological%20Image%20Processing.pdf
     * @param data Matriz de pixels
     * @param i posiçao y da base
     * @param j posicao x da base
     * @param se Elemento Estruturante
     * @return Novo valor da posicao i, j da matriz de dados
     */
    @Override
    public int applyFilter(int[][] data, int i, int j, StructuringElement se) {
        data[i][j] = dilationFilter.applyFilter(data, i, j, se);
        return erosionFilter.applyFilter(data, i, j, se);
    }
    
}
