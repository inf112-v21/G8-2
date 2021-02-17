package sid.roborally;

public class Robot extends GridObject {

    private boolean isDead;
    private boolean hasWon;
    Direction orientation;

    public Robot(int x, int y) {
        super(x,y);
        this.isDead = false;
        this.hasWon = false;
    }

    public boolean isDead() {
        return this.isDead;
    }

    public boolean hasWon() {
        return this.hasWon;
    }
}
