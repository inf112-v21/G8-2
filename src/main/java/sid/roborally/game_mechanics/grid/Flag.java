package sid.roborally.game_mechanics.grid;

/**
 * <h3>Flag</h3>
 * <p>Flag has an id which is used to identify
 * and separate the different flags on the board.. for win condition/flag collecting</p>
 *
 * @author Markus Edlin
 */
public class Flag extends GridObject {
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
