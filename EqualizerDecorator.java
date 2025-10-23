public class EqualizerDecorator extends MediaSourceDecorator {
    public EqualizerDecorator(MediaSource inner) { super(inner); }
    @Override
    public void open() {
        System.out.print(" [EQ]");
        inner.open();
    }
}