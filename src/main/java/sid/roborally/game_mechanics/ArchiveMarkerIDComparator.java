package sid.roborally.game_mechanics;

import sid.roborally.game_mechanics.grid.ArchiveMarker;

import java.util.Comparator;

/**
 * <h3>ArchiveMarkerIDComparator</h3>
 * <p>Comparator for ArchiveMarkerID's</p>
 * @author Markus Edlin
 */
public class ArchiveMarkerIDComparator implements Comparator<ArchiveMarker> {

    @Override
    public int compare(ArchiveMarker o1, ArchiveMarker o2) {
        if(o1.getID() < o2.getID()) return -1;
        if(o1.getID() > o2.getID()) return 1;
        return 0;
    }
}
