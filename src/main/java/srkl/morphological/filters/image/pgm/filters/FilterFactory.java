
package srkl.morphological.filters.image.pgm.filters;
import java.util.HashMap;
import srkl.morphological.filters.image.pgm.common.IPGMImage;
import srkl.morphological.filters.image.pgm.common.StructuringElement;

public class FilterFactory {

    private static final HashMap<Class, IPGMImageFilter> filters;
    
    static {
        filters = new HashMap<>();
        filters.put(ErosionFilter.class,      new ErosionFilter());
        filters.put(DilationFilter.class,     new DilationFilter());
        filters.put(SmoothingFilter.class,    new SmoothingFilter());
        filters.put(GradientFilter.class,     new GradientFilter());
        filters.put(OpeningFilter.class,      new OpeningFilter());
        filters.put(ClosingFilter.class,      new ClosingFilter());
        filters.put(LaplacianFilter.class,    new LaplacianFilter());
        filters.put(ThresholdingFilter.class, new ThresholdingFilter());
    }
    
    public static IPGMImage apply(Class filter, IPGMImage image, StructuringElement se) {
        return filters.get(filter).apply(image, se);
    }
    
    public static IPGMImage applyErosion(IPGMImage image, StructuringElement se) {
        return new ErosionFilter().apply(image, se);
    }
    
    public static IPGMImage applyDilation(IPGMImage image, StructuringElement se) {
        return new DilationFilter().apply(image, se);
    }
    
    public static IPGMImage applySmoothing(IPGMImage image, StructuringElement se) {
        return new SmoothingFilter().apply(image, se);
    }
    
    public static IPGMImage applyGradient(IPGMImage image, StructuringElement se) {
        return new GradientFilter().apply(image, se);
    }
    
    public static IPGMImage applyOpening(IPGMImage image, StructuringElement se) {
        return new OpeningFilter().apply(image, se);
    }
    
    public static IPGMImage applyClosing(IPGMImage image, StructuringElement se) {
        return new ClosingFilter().apply(image, se);
    }
    
    public static IPGMImage applyLaplacian(IPGMImage image, StructuringElement se) {
        return new LaplacianFilter().apply(image, se);
    }
    
    public static IPGMImage applyThresholding(IPGMImage image, StructuringElement se) {
        return new ThresholdingFilter().apply(image, se);
    }

}
