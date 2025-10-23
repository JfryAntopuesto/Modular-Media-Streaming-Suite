public class HlsMediaSource implements MediaSource {
    private final String url;
    private int counter = 0;
    private final int total = 5;
    public HlsMediaSource(String url) { this.url = url; }
    public void open() { System.out.println("Opening HLS stream: " + url); counter = 0; }
    public byte[] readFrame() {
        if (counter >= total) return null;
        counter++;
        return ("hls-frame-" + counter).getBytes();
    }
    public void close() { System.out.println("Closing HLS stream: " + url); }
    public String getName() { return "hls:" + url; }
}