package sid.roborally.game_mechanics.grid;

import java.util.HashSet;

/**
 * <h3>Robot</h3>
 * <p>Class for GridObject-Robot</p>
 * <p>Robot is the board-avatar for a player.</p>
 * @author Markus Edlin
 */
public class Robot extends GridObject {

    private boolean isDead;
    private boolean hasWon;
    HashSet<Flag> flags;
    private int healthPoints;
    private ArchiveMarker archiveMarker;

    public Robot(int x, int y) {
        super(x,y);
        this.isDead = false;
        this.hasWon = false;
        this.healthPoints = 1;
        flags = new HashSet<>();
    }

    public void setHasWon(boolean b) { this.hasWon = b; }
    public boolean hasWon() { return this.hasWon; }

    public int getHealth() { return this.healthPoints; }
    public void setHealth(int health) { this.healthPoints = health; }

    /**
     * @param i Change Robot health by i (positive or negative)
     */
    public void changeHealth(int i) { healthPoints+=i; }

    public void setIsDead(boolean b) { this.isDead = b; }
    public boolean isDead() { return this.isDead; }
    public void setArchiveMarker(ArchiveMarker am){ this.archiveMarker = am; }
    public ArchiveMarker getArchiveMarker(){ return this.archiveMarker ;}

}
