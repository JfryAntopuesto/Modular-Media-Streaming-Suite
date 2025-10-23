import java.util.*;

public class CachedMediaFile implements MediaSource {
    private final MediaSource wrapped;
    private static final Map<String, List<byte[]>> cache = new HashMap<>();
    private List<byte[]> localCopy;
    private int idx = 0;
    public CachedMediaFile(MediaSource wrapped) { this.wrapped = wrapped; }
    public void open() {
        String key = wrapped.getName();
        synchronized (cache) {
            if (cache.containsKey(key)) {
                localCopy = cache.get(key);
                System.out.print(" [CACHE HIT]");
                idx = 0;
                return;
            }
        }
        System.out.print(" [CACHE MISS]");
        wrapped.open();
        List<byte[]> buff = new ArrayList<>();
        byte[] f;
        while ((f = wrapped.readFrame()) != null) {
            buff.add(f);
        }
        wrapped.close();
        synchronized (cache) {
            cache.put(key, buff);
        }
        localCopy = buff;
        idx = 0;
    }
    public byte[] readFrame() {
        if (localCopy == null || idx >= localCopy.size()) return null;
        return localCopy.get(idx++);
    }
    public void close() { /* nothing */ }
    public String getName() { return "cached(" + wrapped.getName() + ")"; }
}