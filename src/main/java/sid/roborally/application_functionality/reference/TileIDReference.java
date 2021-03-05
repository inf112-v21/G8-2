package sid.roborally.application_functionality.reference;

public class TileIDReference {

    public static int flagIndexToId(int flag_index){
        switch(flag_index){
            case 55: return 1;
            case 63: return 2;
            case 71: return 3;
            case 79: return 4;
            default: throw new IllegalArgumentException("No Flag ID associated with index: " + flag_index);
        }
    }

    public static int archiveIndexToID(int archive_index) {
        switch(archive_index){
            case 121: return 1;
            case 122: return 2;
            case 123: return 3;
            case 124: return 4;
            case 129: return 5;
            case 130: return 6;
            case 131: return 7;
            case 132: return 8;
            default: throw new IllegalArgumentException("No ArchiveMarker ID associated with that index: "+ archive_index);
        }
    }
}
