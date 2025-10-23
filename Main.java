import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final PlaylistComposite root = new PlaylistComposite("Root");
    private static final MediaEngine engine = new MediaEngine();

    public static void main(String[] args) {
        System.out.println("=== Modular Media Streaming Suite ===");
        System.out.println("Structural Design Patterns Demo");
        System.out.println("Patterns: Decorator, Composite, Adapter, Facade");
        
        boolean run = true;
        while (run) {
            showMenu();
            int c = getNumber("Choose (1-5): ");
            switch (c) {
                case 1: addItem(); break;
                case 2: playAll(); break;
                case 3: showSystemStatus(); break;
                case 4: demonstrateAdapter(); break;
                case 5: run = false; break;
                default: System.out.println("Invalid"); break;
            }
        }
        scanner.close();
    }

    private static void showMenu() {
        System.out.println("\nMenu: 1=Add item  2=Play playlist  3=System Status  4=Adapter Demo  5=Exit");
    }

    private static void addItem() {
        System.out.println("Source types: hls, local, api");
        String tempSrc;
        while (true) {
            tempSrc = getText("Source: ").trim().toLowerCase();
            if (tempSrc.equals("hls") || tempSrc.equals("local") || tempSrc.equals("api")) break;
            System.out.println("Enter hls/local/api");
        }
        final String src = tempSrc;

        String nameOrUrl = getText("Filename/URL/ID: ").trim();
        boolean useCache = yesNo("Use cache proxy? (y/n): ");
        boolean subtitle = yesNo("Add subtitles? (y/n): ");
        boolean eq = yesNo("Add audio equalizer? (y/n): ");
        boolean watermark = yesNo("Add watermark? (y/n): ");

        final MediaSource srcObj;
        if (src.equals("api")) {
            srcObj = new ApiMediaSource(nameOrUrl);
        } else if (nameOrUrl.startsWith("http://") || nameOrUrl.startsWith("https://") || nameOrUrl.contains(".m3u8")) {
            srcObj = new HlsMediaSource(nameOrUrl);
        } else {
            srcObj = new LocalMediaSource(nameOrUrl);
        }
        MediaSource decorated = srcObj;
        if (useCache) decorated = new CachedMediaFile(decorated);
        if (eq) decorated = new EqualizerDecorator(decorated);
        if (subtitle) decorated = new SubtitleDecorator(decorated);
        if (watermark) decorated = new WatermarkDecorator(decorated);

        // construct a PlaylistItem anonymously; src and srcObj are final as required
        final MediaSource finalDecorated = decorated;
        PlaylistItem item = new PlaylistItem() {
            public void play(MediaEngine engine) { engine.play(finalDecorated); }
            public String title() { return nameOrUrl + " (" + src + ")"; }
        };
        root.add(item);
        System.out.println("Added: " + item.title());
    }

    private static void playAll() {
        if (root.isEmpty()) { System.out.println("Playlist empty."); return; }
        System.out.println("System Status: " + engine.getSystemStatus());
        root.play(engine);
    }

    private static void showSystemStatus() {
        System.out.println("\n=== System Status ===");
        System.out.println(engine.getSystemStatus());
        System.out.println("Playlist items: " + (root.isEmpty() ? "None" : root.getChildCount()));
        System.out.println("Structural patterns active:");
        System.out.println("  - Decorator: MediaSourceDecorator chain");
        System.out.println("  - Composite: PlaylistComposite hierarchy");
        System.out.println("  - Adapter: MediaFormatAdapter for format conversion");
        System.out.println("  - Facade: MediaEngine unified interface");
    }
    
    private static void demonstrateAdapter() {
        System.out.println("\n=== Adapter Pattern Demo ===");
        System.out.println("Demonstrating format adaptation...");
        
        MediaFormatConverter converter = new MediaFormatConverter();
        MediaFormatAdapter adapter = new MediaFormatAdapter(converter, "MP4");
        
        System.out.println("Original format: " + adapter.getOriginalFormat());
        System.out.println("Adapted format: " + adapter.getAdaptedFormat());
        
        // Simulate adapter usage
        adapter.open();
        byte[] frame = adapter.readFrame();
        if (frame != null) {
            System.out.println("Successfully adapted frame: " + frame.length + " bytes");
        }
        adapter.close();
        System.out.println("Adapter pattern demonstration complete!");
    }

    private static boolean yesNo(String prompt) {
        while (true) {
            String r = getText(prompt).trim().toLowerCase();
            if (r.equals("y") || r.equals("yes")) return true;
            if (r.equals("n") || r.equals("no")) return false;
            System.out.println("Enter y or n.");
        }
    }

    private static int getNumber(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int v = scanner.nextInt(); scanner.nextLine();
                return v;
            } else {
                System.out.println("Enter number.");
                scanner.nextLine();
            }
        }
    }

    private static String getText(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
