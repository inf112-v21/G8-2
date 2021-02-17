package sid.roborally;

import java.util.Objects;

/**
 * This Position class keeps track of an x and y value
 * and can print out in the format (x,y) with a toString method.
 * Uses getters and setters to retrieve and set x & y value.
 * Using own equals method to compare positions of two entities.
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
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public String toString(){
        return "("+x+","+ y+")";
    }
    public boolean equals(Position pos) {
        return (this.x == pos.getX() && this.y == pos.getY());
    }
}
