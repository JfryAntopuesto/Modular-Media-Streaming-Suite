/**
 * MediaProcessor - Part of Facade Pattern
 * Handles the complex media processing operations
 */
public class MediaProcessor {
    private boolean isActive = false;
    private int framesProcessed = 0;
    
    public void processFrame(byte[] frame) {
        isActive = true;
        framesProcessed++;
        // Simulate frame processing
        try {
            Thread.sleep(10); // Simulate processing time
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    public String getStatus() {
        return isActive ? "Processing (" + framesProcessed + " frames)" : "Idle";
    }
    
    public void reset() {
        isActive = false;
        framesProcessed = 0;
    }
}
