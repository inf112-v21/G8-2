package sid.roborally;

import org.lwjgl.system.CallbackI;

/**
 * This flag class extends Entity, and has an id which is used to identify
 * and separate the different flags on the board.. for win condition/flag collecting
 *
 * @author Markus Edlin
 */
public class Flag extends Entity{
    int id;

    public Flag(int x, int y, int id) {
        super(x,y);
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }
}
