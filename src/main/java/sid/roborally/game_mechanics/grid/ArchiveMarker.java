package sid.roborally.game_mechanics.grid;

public class ArchiveMarker extends GridObject{

    private int id;

    public ArchiveMarker(int x, int y, int id) {
        super(x, y);
        this.id = id;
    }
    public int getID(){return id;}
}
