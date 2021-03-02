package sid.roborally.game_mechanics.card;

/**
 * This is the general card class
 * It describes variables all cards need
 * If anything is added to this class,
 * those variables must then be added
 * to the call to this constructor of
 * classes inheriting from this class
 *
 * @author Emil ELdooen
 */
public class Card {
    //Priority, determines the order a card is played in, higher priority wins
    private int priority;
    private String name;

    //Constructor giving card its priority
    public Card(int pri, String name){
        this.priority = pri;
        this.name = name;
    }

    /**
     * <p>Returns card-name/type</p>
     */
    public String getName() { return name; }

    //Allows other classes to access a card's priority
    public int getPriority(){
        return priority;
    }
}
