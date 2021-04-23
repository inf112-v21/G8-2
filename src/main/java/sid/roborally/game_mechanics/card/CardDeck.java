package sid.roborally.game_mechanics.card;

import java.io.Serializable;
import java.util.Collections;
import java.util.Stack;

/**
 * <h3>CardDeck</h3>
 * <p>Sets up a card-deck with correct-priority</p>
 *
 * @author Andreas Henriksen
 */
public class CardDeck implements Serializable {

    private Stack<Card> deck;

    public CardDeck() {
        deck = new Stack<>();
        addMoveCards(deck);
        addRotationCards(deck);
    }

    /**
     * Gets the current deck
     * @return the current deck
     */
    public Stack<Card> getDeckStack() {
        return deck;
    }

    public int size() { return deck.size(); }

    /**
     * Gives the next card (and removes it from the deck)
     * @return Card
     */
    public Card getNextCard() { return deck.pop(); }

    /**
     * Shuffles the card-deck
     */
    public void shuffle() {
        Collections.shuffle(deck);
    }

    /**
     * Adds all the rotation cards to the deck
     * @param deck CardDeck
     */
    private void addRotationCards(Stack<Card> deck) {
        
        for (int i = 0; i < 18; i++) {
            deck.push(new TurnCard((80 + i*20),
                    CardAction.TURN_RIGHT));
            deck.push(new TurnCard((70 + i*20),
                    CardAction.TURN_LEFT));
            if (i > 11)
                deck.push(new TurnCard((10 + i),
                        CardAction.TURN_AROUND));
        }
    }

    /**
     * Adds all the movement cards to the deck
     * @param deck CardDeck
     */
    private void addMoveCards(Stack<Card> deck) {

        for (int i = 0; i < 18; i++) {
            deck.push(new StepCard((490 + i*10),
                    1, CardAction.FORWARD));

            if (i > 5) {
                deck.push(new StepCard((670 + i*10),
                        2, CardAction.FORWARD));
            }

            if (i > 11) {
                deck.push(new StepCard((790 + i*20),
                        3, CardAction.FORWARD));
                deck.push(new StepCard((430 + i),
                        1, CardAction.BACKWARD));
            }
        }
    }
}
