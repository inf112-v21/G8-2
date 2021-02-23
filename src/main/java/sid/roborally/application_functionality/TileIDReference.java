package sid.roborally.application_functionality;

import sid.roborally.game_mechanics.grid.Flag;

import java.util.HashMap;

public class TileIDReference {

    public static int flagIndexToId(int flag_index){
        if(flag_index == 55) return 1;
        else if(flag_index == 63) return 2;
        else if(flag_index == 71) return 3;
        else return 4;
    }

}
