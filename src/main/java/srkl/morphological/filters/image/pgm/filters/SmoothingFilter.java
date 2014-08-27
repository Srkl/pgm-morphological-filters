
package srkl.morphological.filters.image.pgm.filters;

import srkl.morphological.filters.image.pgm.common.StructuringElement;

public class SmoothingFilter extends AbstractMorphoFilter {

    private final AbstractMorphoFilter openingFilter;
    private final AbstractMorphoFilter closingFilter;

    public SmoothingFilter() {
        openingFilter = new OpeningFilter();
        closingFilter = new ClosingFilter();
    }
    
    @Override
    public int applyFilter(int[][] data, int i, int j, StructuringElement se) {
        data[i][j] = openingFilter.applyFilter(data, i, j, se);
        return closingFilter.applyFilter(data, i, j, se);
    }
    
}
