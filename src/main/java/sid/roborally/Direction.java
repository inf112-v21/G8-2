package sid.roborally;

/**
 *  Directions that can be used by any GridObject.
 *(
 *
 *  @author Terje Trommestad
 */


//TODO: 18/02/2021 Decide to use Switch or Rotate. Intended usage with switch: Direction.NORTH.left() returns WEST
//
//SWITCH



public enum Direction {
    NORTH {
        Direction left() {
            return WEST;
        }

        Direction right() {
            return EAST;
        }
    },
    EAST {
        Direction left() {
            return NORTH;
        }

        Direction right() {
            return SOUTH;
        }
    },
    SOUTH {
        Direction left() {
            return EAST;
        }

        Direction right() {
            return WEST;
        }
    },
    WEST {
        Direction left() {
            return SOUTH;
        }

        Direction right() {
            return NORTH;
        }

        //abstract Direction left();
        //abstract Direction right();


        // TODO: 18/02/2021 better solution?
        // Rotate right (90 degrees clocwise)
        public Direction rotateRight() {
            return values()[(ordinal() + 1) % 4];
        }

        // Rotate 180 degrees
        // public Direction rotate180() {
        //     return values()[(ordinal() + 2) % 4];
        // }

        // Rotate left (270 degrees clockwise (90 counterclockwise))
        public Direction rotateLeft() {
            return values()[(ordinal() + 3) % 4];
        }


    }
}
