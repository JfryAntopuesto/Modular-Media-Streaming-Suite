public class WatermarkDecorator extends MediaSourceDecorator {
    public WatermarkDecorator(MediaSource inner) { super(inner); }
    @Override
    public byte[] readFrame() {
        byte[] f = inner.readFrame();
        if (f != null) {
            System.out.print(" [WM]");
        }
        return f;
    }
}