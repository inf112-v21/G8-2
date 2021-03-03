package sid.roborally.game_mechanics;

import org.junit.Before;
import org.junit.Test;
import sid.roborally.game_mechanics.card.Card;
import sid.roborally.game_mechanics.card.CardDealer;

import java.util.Stack;

import static org.junit.Assert.*;

public class DealerTest {
    //TODO: Sannsynligvis ikke nødvendigStack<Card> testDeck = new Stack<Card>();
    CardDealer testDealer;

    @Before
    public void init(){

        //TODO: CardDealer lager CardDeck internt
        /*
        //For-loop creating test cards for test deck
        for(int i = 0; i<10; i++){
            StepCard testCard = new StepCard(100 + (10*i), i, "TESTCARD");
            testDeck.add(testCard);
        }
        */

        testDealer = new CardDealer();
    }

    @Test
    public void shuffleChangesDeck() {
        Stack<Card> originalDeck = (Stack<Card>) testDealer.getDeck().getDeckStack().clone();
        /* Checking that they are equal first */
        assertEquals(originalDeck, testDealer.getDeck().getDeckStack());

        /* Testing that shuffle changes it */
        testDealer.shuffleDeck();
        assertNotEquals(originalDeck, testDealer.getDeck().getDeckStack());
    }

    @Test
    public void resetDeckWillResetDeck() {
        int originalDeckSize = testDealer.getDeck().size();

        /* Checking that removing cards will change deck */
        testDealer.dealCards(5);
        assertNotEquals(originalDeckSize, testDealer.getDeck().size());

        /* Checking that reset will result in deck with original size */
        testDealer.resetDeck();
        assertEquals(originalDeckSize, testDealer.getDeck().size());
    }

    //TODO: Overflødig siden Dealer ikke mottar et deck som parameter
    /*
    @Test
    public void deckHasCards(){
        assertEquals(testDealer.getDeckStack().size(),testDeck.size());
    }
    */

    /* //TODO: Det er nok å sjekke dette i card-deck. Har lagt til denne testen i CardDeck
    @Test
    public void cardsRemovedWhenDealt(){
        Card removedCard = testDealer.deal();
        assertFalse(testDealer.getDeckStack().contains(removedCard));
    }
    */
}
