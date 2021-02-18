package sid.roborally.game_mechanics;

import java.util.ArrayList;

public class Robot extends GridObject {

    private boolean isDead;
    private boolean hasWon;
    ArrayList<Flag> flags;
    private int health;
    Direction orientation;

    public Robot(int x, int y) {
        super(x,y);
        this.isDead = false;
        this.hasWon = false;
        this.health = 1;
        flags = new ArrayList<>();
    }

    public boolean isDead() {
        return this.isDead;
    }

    public boolean hasWon() {
        return this.hasWon;
    }

    public int getHealth() {
        return this.health;
    }

    public void setIsDead(boolean b) {
        this.isDead = b;
    }

    public void setHasWon(boolean b) {
        this.hasWon = b;
    }
}
