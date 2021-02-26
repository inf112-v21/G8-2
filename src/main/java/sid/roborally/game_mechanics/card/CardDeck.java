package sid.roborally.game_mechanics.card;

import java.util.Stack;

/**
 * This is the card deck class
 * It sets up cards with their correct priority
 * and adds them to the deck
 */
public class CardDeck {

    Stack<Card> deck;

    public CardDeck() {
        deck = new Stack<>();
        addMoveCards(deck);
        addRotatingCards(deck);
    }

    /**
     * Gets the current deck
     * @return the current deck
     */
    public Stack<Card> getDeck() {
        return deck;
    }

    /**
     * Adds all the rotation cards to the deck
     * @param deck
     */
    private void addRotatingCards(Stack<Card> deck) {
        for (int i = 0; i < 18; i++) {
            deck.push(new TurnCard(generatePriority(80, 420, 20), "right"));
            deck.push(new TurnCard(generatePriority(70, 410, 20), "left"));
            if (i > 11)
                deck.push(new TurnCard(generatePriority(10, 60), "around"));
        }
    }

    /**
     * Adds all the movement cards to the deck
     * @param deck
     */
    private void addMoveCards(Stack<Card> deck) {
        for (int i = 0; i < 18; i++) {
            deck.push(new StepCard(generatePriority(490, 650, 10), 1));

            if (i > 5) {
                deck.push(new StepCard(generatePriority(670, 780, 10), 2));
            }

            if (i > 11) {
                deck.push(new StepCard(generatePriority(790, 840, 10), 3));
                deck.push(new StepCard(generatePriority(430, 480), -1));
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
