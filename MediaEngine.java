/**
 * MediaEngine - Facade Pattern Implementation
 * Provides a simplified interface to the complex media processing subsystem
 */
public class MediaEngine {
    private final MediaProcessor processor;
    private final PlaylistManager playlistManager;
    
    public MediaEngine() {
        this.processor = new MediaProcessor();
        this.playlistManager = new PlaylistManager();
    }

    public void play(MediaSource source) {
        System.out.print("  Playing: " + source.getName() + " ");
        source.open();
        byte[] f;
        int frameCount = 0;
        while ((f = source.readFrame()) != null) {
            processor.processFrame(f);
            frameCount++;
            if (frameCount % 10 == 0) System.out.print(".");
            try { Thread.sleep(50); } catch (InterruptedException ignored) {}
        }
        source.close();
        System.out.println(" [DONE]");
    }
    
    public void playPlaylist(PlaylistComposite playlist) {
        playlistManager.playPlaylist(playlist, this);
    }
    
    public String getSystemStatus() {
        return "MediaEngine: Active | Processor: " + processor.getStatus() + 
               " | PlaylistManager: " + playlistManager.getStatus();
    }
}