package sid.roborally.application_functionality;

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

}
