public abstract class MediaSourceDecorator implements MediaSource {
    protected final MediaSource inner;
    public MediaSourceDecorator(MediaSource inner) { this.inner = inner; }
    public void open() { inner.open(); }
    public byte[] readFrame() { return inner.readFrame(); }
    public void close() { inner.close(); }
    public String getName() { return inner.getName(); }
}