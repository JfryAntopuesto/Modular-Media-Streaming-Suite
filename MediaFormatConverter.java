/**
 * MediaFormatConverter - Part of Adapter Pattern
 * Handles the conversion between different media formats
 */
public class MediaFormatConverter {
    private boolean isInitialized = false;
    private int frameCount = 0;
    private final int maxFrames = 50; // Simulate limited frames
    
    public void initialize() {
        isInitialized = true;
        System.out.println("Format converter initialized");
    }
    
    public byte[] getNextFrame() {
        if (!isInitialized || frameCount >= maxFrames) {
            return null;
        }
        frameCount++;
        return new byte[1024]; // Simulate frame data
    }
    
    public byte[] convertFrame(byte[] rawFrame) {
        // Simulate format conversion
        byte[] convertedFrame = new byte[rawFrame.length];
        System.arraycopy(rawFrame, 0, convertedFrame, 0, rawFrame.length);
        return convertedFrame;
    }
    
    public void cleanup() {
        isInitialized = false;
        frameCount = 0;
        System.out.println("Format converter cleaned up");
    }
    
    public boolean isInitialized() {
        return isInitialized;
    }
}
