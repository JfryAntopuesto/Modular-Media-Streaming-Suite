public class LocalMediaSource implements MediaSource {
    private final String path;
    private int counter = 0;
    private final int total = 3;
    public LocalMediaSource(String path) { this.path = path; }
    public void open() { System.out.println("Opening local file: " + path); counter = 0; }
    public byte[] readFrame() {
        if (counter >= total) return null;
        counter++;
        return ("local-frame-" + counter).getBytes();
    }
    public void close() { System.out.println("Closing local file: " + path); }
    public String getName() { return "local:" + path; }
}