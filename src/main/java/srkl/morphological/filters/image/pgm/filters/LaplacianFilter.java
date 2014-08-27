
package srkl.morphological.filters.image.pgm.filters;

import srkl.morphological.filters.image.pgm.common.StructuringElement;

public class LaplacianFilter extends AbstractMorphoFilter {
    
    private final AbstractMorphoFilter dilationFilter;
    private final AbstractMorphoFilter erosionFilter;

    public LaplacianFilter() {
        dilationFilter = new DilationFilter();
        erosionFilter  = new ErosionFilter();
    }
    
    /**
     * Aplica filtro Laplaciano.
     * Eh definido pela diferenca do gradiente interno e o gradiente externo.
     * http://www.mif.vu.lt/atpazinimas/dip/FIP/fip-Morpholo.html#Heading107
     * @param data Matriz de pixels
     * @param i posiçao y da base
     * @param j posicao x da base
     * @param se Elemento Estruturante
     * @return Novo valor da posicao i, j da matriz de dados
     */
    @Override
    public int applyFilter(int[][] data, int i, int j, StructuringElement se) {
        final int value = data[i][j];
        final int dilationResult = dilationFilter.applyFilter(data, i, j, se);
        final int erosionResult  = erosionFilter.applyFilter(data, i, j, se);
        return (dilationResult - value) - (value - erosionResult);
    }
    
}
