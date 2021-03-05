package sid.roborally.game_mechanics.grid;

/**
 * <h3>Position</h3>
 * <p>This Position class keeps track of an x and y value
 * and can print out in the format (x,y) with a toString method.
 * Uses getters and setters to retrieve and set x & y value.
 * Using own equals method to compare positions of two entities.</p>
 *
 * @author Markus Edlin
 */
public class Position {

    private int x;
    private int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public String toString(){
        return "("+x+","+ y+")";
    }
    public boolean equals(Position pos) {
        return (this.x == pos.getX() && this.y == pos.getY());
    }

    public void setPosition(Position newPos) {
        this.x = newPos.getX();
        this.y = newPos.getY();
    }

    /**
     * change current position values by x and y
     *  @param x difference in x direction
     * @param y difference in y direction
     * @return Position
     */
    public Position increment(int x, int y) {
        setPosition(new Position(this.x+x, this.y+y));
        return this;
    }
}
