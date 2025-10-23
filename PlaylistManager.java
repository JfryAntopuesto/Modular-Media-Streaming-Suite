/**
 * PlaylistManager - Part of Facade Pattern
 * Manages playlist operations and coordination
 */
public class PlaylistManager {
    private boolean isActive = false;
    private int playlistsManaged = 0;
    
    public void playPlaylist(PlaylistComposite playlist, MediaEngine engine) {
        isActive = true;
        playlistsManaged++;
        playlist.play(engine);
        isActive = false;
    }
    
    public String getStatus() {
        return isActive ? "Managing Playlist" : "Ready (" + playlistsManaged + " managed)";
    }
    
    public void reset() {
        isActive = false;
        playlistsManaged = 0;
    }
}
