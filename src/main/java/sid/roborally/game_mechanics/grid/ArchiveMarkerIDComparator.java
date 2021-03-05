package sid.roborally.game_mechanics.grid;

import java.util.Comparator;

public class ArchiveMarkerIDComparator implements Comparator<ArchiveMarker> {

    @Override
    public int compare(ArchiveMarker o1, ArchiveMarker o2) {
        if(o1.getID() < o2.getID()) return -1;
        if(o1.getID() > o2.getID()) return 1;
        return 0;
    }
}
