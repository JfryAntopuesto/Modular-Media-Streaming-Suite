public interface MediaSource {
    void open();
    byte[] readFrame(); // returns null when done
    void close();
    String getName();
}