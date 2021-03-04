package sid.roborally.game_mechanics;

import org.junit.Before;
import org.junit.Test;
import sid.roborally.game_mechanics.card.Card;
import sid.roborally.game_mechanics.card.CardDealer;

import java.util.Stack;

import static org.junit.Assert.*;

public class DealerTest {
    CardDealer testDealer;

    @Before
    public void init(){

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

}
