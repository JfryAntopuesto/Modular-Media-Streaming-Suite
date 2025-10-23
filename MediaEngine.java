public class MediaEngine {
    private Renderer renderer = new HardwareRenderer();
    public void setRenderer(Renderer r) { this.renderer = r; }
    public String getRendererName() { return renderer.name(); }

    public void play(MediaSource source) {
        System.out.print("  Playing: " + source.getName() + " ");
        source.open();
        byte[] f;
        int frameCount = 0;
        while ((f = source.readFrame()) != null) {
            renderer.renderFrame(f);
            frameCount++;
            if (frameCount % 10 == 0) System.out.print(".");
            try { Thread.sleep(50); } catch (InterruptedException ignored) {}
        }
        source.close();
        System.out.println(" [DONE]");
    }
}