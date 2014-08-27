
package srkl.morphological.filters.image.pgm.filters;

import srkl.morphological.filters.image.pgm.common.IPGMImage;
import srkl.morphological.filters.image.pgm.common.StructuringElement;

public interface IPGMImageFilter {

    public IPGMImage apply(IPGMImage image, StructuringElement se);
    
}
