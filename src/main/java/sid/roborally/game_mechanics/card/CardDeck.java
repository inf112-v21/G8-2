package sid.roborally.game_mechanics.card;

import java.util.Collections;
import java.util.Stack;

/**
 * This is the card deck class
 * It sets up cards with their correct priority
 * and adds them to the deck
 */
public class CardDeck {

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
            deck.push(new TurnCard(generatePriority(80, 420, 20), CardAction.TURN_RIGHT));
            deck.push(new TurnCard(generatePriority(70, 410, 20), CardAction.TURN_LEFT));
            if (i > 11)
                deck.push(new TurnCard(generatePriority(10, 60), CardAction.TURN_AROUND));
        }
    }

    /**
     * Adds all the movement cards to the deck
     * @param deck CardDeck
     */
    private void addMoveCards(Stack<Card> deck) {
        for (int i = 0; i < 18; i++) {
            deck.push(new StepCard(generatePriority(490, 650, 10), 1, CardAction.FORWARD));

            if (i > 5) {
                deck.push(new StepCard(generatePriority(670, 780, 10), 2, CardAction.FORWARD));
            }

            if (i > 11) {
                deck.push(new StepCard(generatePriority(790, 840, 10), 3, CardAction.FORWARD));
                deck.push(new StepCard(generatePriority(430, 480), 1, CardAction.BACKWARD));
            }
        }
    }

    /**
     * Generates a random priority number between the lowerBound and higherBound
     * @param lowerBound lowest priority number
     * @param upperBound highest priority number
     * @return a random priority number
     */
    public int generatePriority(int lowerBound, int upperBound) {
        return (int) (lowerBound + (Math.random() * (upperBound - lowerBound+1)));
    }

    /**
     * Generates a random priority number between the lowerBound and upperBound
     * with a given step between each number
     * @param lowerBound lowest priority number
     * @param upperBound highest priority number
     * @param step the step there should be between each number
     * @return a random priority number
     */
    public int generatePriority(int lowerBound, int upperBound, int step) {
        int rand = (int) (Math.random() * (upperBound-lowerBound+1));
        return rand - (rand % step) + lowerBound;
    }
}
