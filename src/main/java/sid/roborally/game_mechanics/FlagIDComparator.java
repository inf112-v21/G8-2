package sid.roborally.game_mechanics;

import sid.roborally.game_mechanics.grid.Flag;

import java.util.Comparator;

public class FlagIDComparator implements Comparator<Flag> {

    @Override
    public int compare(Flag o1, Flag o2) {
        if(o1.getId() < o2.getId()) return -1;
        if(o1.getId() > o2.getId()) return 1;
        return 0;
    }
}
