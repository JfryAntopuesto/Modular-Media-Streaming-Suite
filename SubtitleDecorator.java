public class SubtitleDecorator extends MediaSourceDecorator {
    public SubtitleDecorator(MediaSource inner) { super(inner); }
    @Override
    public byte[] readFrame() {
        byte[] f = inner.readFrame();
        if (f != null) {
            System.out.print(" [SUB]");
        }
        return f;
    }
}