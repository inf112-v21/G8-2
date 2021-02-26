package sid.roborally.application_functionality.reference;

public class TileIDReference {

    public static int flagIndexToId(int flag_index){
        switch(flag_index){
            case 55: return 1;
            case 63: return 2;
            case 71: return 3;
            default: return 4;
        }
    }

}
