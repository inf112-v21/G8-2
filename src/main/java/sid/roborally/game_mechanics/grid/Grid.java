package sid.roborally.game_mechanics.grid;

import sid.roborally.game_mechanics.Direction;

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
    public void addGridObject(GridObject o) {
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
            //first removes the object from the grid, temporarily
            removeGridObject(o);
            //sets the position of the GridObject
            o.setPosition(newPos);
            addGridObject(o);
    }
    public boolean robotCanMoveToPosition(Robot r, Position pos){
        if(!checkInBounds(pos)) return false;
        if(containsRobot(pos)) return false;
        return true;
    }

    public void removeGridObject(GridObject o) {
        if(getGridObjectsFromPosition(o.getPosition()).contains(o)) grid.get(o.getPosition().getX()).get(o.getPosition().getY()).remove(o);
        else throw new IllegalArgumentException("Position"+ o.getPosition() + "does not contain this object.");
    }

    /**
     * Move GridObject 1 value in 1 Direction
     *
     * @param r Grid Object
     * @param dir Direction
     */
    public void moveRobot(Robot r, Direction dir) {
        Position newPos = getNewPositionFromDirection(r,dir);
        if(!robotCanMoveToPosition(r,newPos)) return;
        moveGridObjectToNewPosition(r, newPos);
    }

    public Position getNewPositionFromDirection(GridObject o, Direction dir){
        Position currentPos = o.getPosition();
        switch(dir){
            case NORTH:
                return new Position(currentPos.getX(), currentPos.getY()+1);
            case EAST:
                return new Position(currentPos.getX()+1, currentPos.getY());
            case SOUTH:
                return new Position(currentPos.getX(), currentPos.getY()-1);
            case WEST:
                return new Position(currentPos.getX()-1, currentPos.getY());
        }
        return o.getPosition();
    }

    public boolean positionHasHole(Position pos) {
        HashSet<GridObject> objects = getGridObjectsFromPosition(pos);
        for (GridObject o : objects) if (o instanceof Hole) return true;
        return false;
    }

    public boolean positionHasFlag(Position pos) {
        HashSet<GridObject> objects = getGridObjectsFromPosition(pos);
        for (GridObject o : objects) if (o instanceof Flag) return true;
        return false;
    }

    public Flag getFlagAtPosition(Position pos)
    {
        HashSet<GridObject> objects = getGridObjectsFromPosition(pos);
        for (GridObject o : objects) if (o instanceof Flag) return (Flag) o;
        return null;
    }
}
