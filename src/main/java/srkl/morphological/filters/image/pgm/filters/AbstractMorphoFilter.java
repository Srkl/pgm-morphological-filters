
package srkl.morphological.filters.image.pgm.filters;

import srkl.morphological.filters.image.pgm.common.IPGMImage;
import srkl.morphological.filters.image.pgm.common.StructuringElement;

public abstract class AbstractMorphoFilter implements IPGMImageFilter {

    @Override
    public IPGMImage apply(IPGMImage input, StructuringElement se) {
        final IPGMImage ouput = input.clone();
        final int width = input.getData().length;
        final int height= input.getData()[0].length;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                ouput.setData(i, j, applyFilter(input.getData(), i, j, se));
            }
        }

        return ouput;
    }
    
    public abstract int applyFilter(int[][] data, int i, int j, StructuringElement se);
}
