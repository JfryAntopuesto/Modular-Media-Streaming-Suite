import java.util.*;
public class PlaylistComposite implements PlaylistItem {
    private final String name;
    private final List<PlaylistItem> children = new ArrayList<>();
    public PlaylistComposite(String name) { this.name = name; }
    public void add(PlaylistItem i) { children.add(i); }
    public void remove(int idx) { children.remove(idx); }
    public boolean isEmpty() { return children.isEmpty(); }
    public void play(MediaEngine engine) {
        System.out.println("\n=== Playing Playlist: " + name + " ===");
        System.out.println("Items: " + children.size());
        System.out.println("----------------------------------------");
        
        for (int i = 0; i < children.size(); i++) {
            System.out.println("\n[" + (i+1) + "/" + children.size() + "] " + children.get(i).title());
            children.get(i).play(engine);
        }
        
        System.out.println("\n----------------------------------------");
        System.out.println("Playlist completed successfully!");
    }
    public String title() { return name; }
    public void list() {
        for (int i = 0; i < children.size(); i++) {
            System.out.println((i+1) + ". " + children.get(i).title());
        }
    }
    
    public int getChildCount() {
        return children.size();
    }
    
    public void display() {
        System.out.println("Playlist: " + name + " (" + children.size() + " items)");
        for (int i = 0; i < children.size(); i++) {
            System.out.println("  " + (i+1) + ". " + children.get(i).title());
        }
    }
}