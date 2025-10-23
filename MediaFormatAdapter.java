/**
 * MediaFormatAdapter - Adapter Pattern Implementation
 * Adapts different media formats to a common MediaSource interface
 */
public class MediaFormatAdapter implements MediaSource {
    private final MediaFormatConverter converter;
    private final String originalFormat;
    private final String adaptedFormat;
    
    public MediaFormatAdapter(MediaFormatConverter converter, String originalFormat) {
        this.converter = converter;
        this.originalFormat = originalFormat;
        this.adaptedFormat = "Standard";
    }
    
    @Override
    public void open() {
        System.out.println("Opening " + originalFormat + " format, adapting to " + adaptedFormat);
        converter.initialize();
    }
    
    @Override
    public byte[] readFrame() {
        byte[] rawFrame = converter.getNextFrame();
        if (rawFrame != null) {
            return converter.convertFrame(rawFrame);
        }
        return null;
    }
    
    @Override
    public void close() {
        System.out.println("Closing " + originalFormat + " format adapter");
        converter.cleanup();
    }
    
    @Override
    public String getName() {
        return "Adapted " + originalFormat + " Media";
    }
    
    public String getOriginalFormat() {
        return originalFormat;
    }
    
    public String getAdaptedFormat() {
        return adaptedFormat;
    }
}
