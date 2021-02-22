package sid.roborally.game_mechanics;

import java.util.Collections;
import java.util.Stack;

/**
 * This is the card dealer class
 * This class keeps track of the cards in a given round
 * Gives cards to players
 * And keeps track of how many cards are in the game
 */
public class CardDealer {
    //Deck of cards in the game
    Stack<Card> deck;

    //Constructor giving the dealer its deck
    public CardDealer(Stack<Card> givenDeck) {
        this.deck = givenDeck;
        Collections.shuffle(deck);
    }

    //Gets the deck from the dealer
    public Stack<Card> getDeck(){
        return deck;
    }

    //Gives a card and removes it from the deck
    public Card deal(){
        return deck.pop();
    }

    //Shuffles the dealer's cards
    public void shuffleDeck(){
        Collections.shuffle(deck);
    }
}
