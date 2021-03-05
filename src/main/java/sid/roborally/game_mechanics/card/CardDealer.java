package sid.roborally.game_mechanics.card;

import java.util.ArrayList;
import java.util.List;

/**
 * <h3>CardDealer</h3>
 * <p>Keeps track of game cards, and can be called upon to deal cards to players.</p>
 *
 * @author Emil Eldooen
 */
public class CardDealer {

    private CardDeck deck;

    public CardDealer() { deck = new CardDeck(); }

    /**
     * <p>Resets card-deck</p>
     */
    public void resetDeck() { deck = new CardDeck(); }

    /**
     * <p>Get card-deck.</p>
     * @return CardDeck instance
     */
    public CardDeck getDeck(){
        return deck;
    }

    /**
     * <p>Deals a List with a given amount of cards</p>
     * @param amount Amount of cards to be dealt
     * @return List of cards
     */
    public List<Card> dealCards(int amount) {
        List<Card> retList = new ArrayList<>();
        for(int i = 0; i < amount; i++) retList.add(deck.getNextCard());
        return retList;
    }

    /**
     * <p>Shuffles card-deck</p>
     */
    public void shuffleDeck(){
        deck.shuffle();
    }

}
