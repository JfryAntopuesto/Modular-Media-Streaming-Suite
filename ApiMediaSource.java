public class ApiMediaSource implements MediaSource {
    private final String id;
    private int counter = 0;
    private final int total = 4;
    public ApiMediaSource(String id) { this.id = id; }
    public void open() { System.out.println("Fetching from API: " + id); counter = 0; }
    public byte[] readFrame() {
        if (counter >= total) return null;
        counter++;
        return ("api-frame-" + counter).getBytes();
    }
    public void close() { System.out.println("Done API fetch: " + id); }
    public String getName() { return "api:" + id; }
}