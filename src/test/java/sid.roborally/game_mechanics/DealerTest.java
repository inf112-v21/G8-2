package sid.roborally.game_mechanics;

import org.junit.Before;
import org.junit.Test;
import sid.roborally.game_mechanics.card.Card;
import sid.roborally.game_mechanics.card.CardDealer;
import sid.roborally.game_mechanics.card.StepCard;

import java.util.Stack;

import static org.junit.Assert.*;

public class DealerTest {
    Stack<Card> testDeck = new Stack<Card>();
    CardDealer testDealer;
    @Before
    public void init(){
        //For-loop creating test cards for test deck
        for(int i = 0; i<10; i++){
            StepCard testCard = new StepCard(100 + (10*i), i, "TESTCARD");
            testDeck.add(testCard);
        }
        testDealer = new CardDealer(testDeck);
    }
    @Test
    public void deckHasCards(){
        assertEquals(testDealer.getDeck().size(),testDeck.size());
    }
    @Test
    public void cardsRemovedWhenDealt(){
        Card removedCard = testDealer.deal();
        assertFalse(testDealer.getDeck().contains(removedCard));
    }
}
