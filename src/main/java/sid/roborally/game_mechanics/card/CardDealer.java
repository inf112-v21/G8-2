package sid.roborally.game_mechanics.card;

import java.util.ArrayList;
import java.util.List;

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
    private CardDeck deck;

    //Constructor giving the dealer its deck
    public CardDealer() { deck = new CardDeck(); }

    /**
     * <p>Resets card-deck</p>
     */
    public void resetDeck() { deck = new CardDeck(); }

    //Gets the deck from the dealer
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

    //Gives a card and removes it from the deck
    public Card deal(){
        return deck.getNextCard();
    }

    //Shuffles the dealer's cards
    public void shuffleDeck(){
        deck.shuffle();
    }

}
