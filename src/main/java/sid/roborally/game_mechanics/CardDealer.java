package sid.roborally.game_mechanics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * This is the card dealer class
 * This class keeps track of the cards in a given round
 * Gives cards to players
 * And keeps track of how many cards are in the game
 *
 * @author Emil Eldooen
 */
public class CardDealer {
    //Deck of cards in the game
    private Stack<Card> deck;
    private ArrayList<Card> usedCards = new ArrayList<Card>();

    //Constructor giving the dealer its deck
    public CardDealer(Stack<Card> givenDeck) {
        this.deck = givenDeck;
        Collections.shuffle(deck);
    }

    //Gets the deck from the dealer
    public Stack<Card> getDeck(){
        return deck;
    }

    //Gets the used cards from the dealer
    public ArrayList<Card> getUsedCards(){
        return usedCards;
    }

    //Gives a card and removes it from the deck
    public Card deal(){
        return deck.pop();
    }

    //Shuffles the dealer's cards
    public void shuffleDeck(){
        Collections.shuffle(deck);
    }

    //Adds a card used by a player to the list
    public void cardUsed(Card card){
        usedCards.add(card);
    }

}
