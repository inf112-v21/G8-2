package sid.roborally.game_mechanics;

import java.util.ArrayList;
import java.util.HashSet;

public class Grid {
    ArrayList<ArrayList<HashSet<GridObject>>> grid;

    private int width;
    private int height;

    public Grid(int w, int h) {
        this.width = w;
        this.height = h;
        grid = new ArrayList<>();
        for(int i = 0; i<width; i++){
            grid.add(new ArrayList<>());
            for(int j = 0; j<height; j++){
                grid.get(i).add(new HashSet<>());
            }
        }
    }

    public boolean containsRobot(Position pos) {
        int x = pos.getX();
        int y = pos.getY();
        HashSet<GridObject> thisPosition = grid.get(x).get(y);
        for(GridObject o : thisPosition) if(o instanceof Robot) return true;
        return false;
    }

    public boolean checkInBounds(Position pos) {
        return ((pos.getX()<width && pos.getX()>=0) && (pos.getY()<height && pos.getY()>=0));
    }

    public HashSet<GridObject> getGridObjectsFromPosition(Position pos) {
        return grid.get(pos.getX()).get(pos.getY());
    }

    /**
     * Adds to grid if position is inbounds and if there isn't a robot at that same position
     *
     * @param o GridObject
     */
    public void addGridObjectToGrid(GridObject o) {
        if(checkInBounds(o.getPosition())) grid.get(o.getPosition().getX()).get(o.getPosition().getY()).add(o);
    }


    /**
     * Removes current GridObject from grid.
     * Changes position of GridObject to given newPos.
     * Adds GridObject to grid with new position.
     *
     * @param o GridObject to move
     * @param newPos new Position to move GridObject to
     */
    public void moveGridObjectToNewPosition(GridObject o, Position newPos) {
        if(getGridObjectsFromPosition(o.getPosition()).contains(o)){
            removeGridObjectFromGrid(o);
            o.setPosition(newPos.getX(), newPos.getY());
            addGridObjectToGrid(o);
        }
    }

    public void removeGridObjectFromGrid(GridObject o) {

        if(getGridObjectsFromPosition(o.getPosition()).contains(o)) grid.get(o.getPosition().getX()).get(o.getPosition().getY()).remove(o);
        else throw new IllegalArgumentException("Position"+ o.getPosition() + "does not contain this object.");
    }
}
