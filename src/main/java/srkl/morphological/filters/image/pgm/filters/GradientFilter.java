
package srkl.morphological.filters.image.pgm.filters;

import srkl.morphological.filters.image.pgm.common.StructuringElement;

public class GradientFilter extends AbstractMorphoFilter {
    
    private final AbstractMorphoFilter dilationFilter;
    private final AbstractMorphoFilter erosionFilter;

    public GradientFilter() {
        dilationFilter = new DilationFilter();
        erosionFilter  = new ErosionFilter();
    }

    /**
     * Aplica filtro Gradiente.
     * http://en.wikipedia.org/wiki/Morphological_gradient
     * @param data Matriz de pixels
     * @param i posiçao y da base
     * @param j posicao x da base
     * @param se Elemento Estruturante
     * @return Novo valor da posicao i, j da matriz de dados
     */
    @Override
    public int applyFilter(int[][] data, int i, int j, StructuringElement se) {
        final int dilationResult = dilationFilter.applyFilter(data, i, j, se);
        final int erosionResult  = erosionFilter.applyFilter(data, i, j, se);
        return dilationResult - erosionResult;
    }
    
}
