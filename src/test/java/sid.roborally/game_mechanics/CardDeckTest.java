package sid.roborally.game_mechanics;

import org.junit.Test;
import sid.roborally.game_mechanics.card.Card;
import sid.roborally.game_mechanics.card.CardDeck;

import java.util.ArrayList;
import java.util.Stack;

import static org.junit.Assert.*;

public class CardDeckTest {

    CardDeck deck = new CardDeck();

    @Test
    public void shouldReturnPriorityInRange() {
        int priority = deck.generatePriority(430, 480);
        assertTrue(430 <= priority && priority <= 480);
    }

    @Test
    public void deckHoldsAllCards() {
        Stack<Card> deck;
        CardDeck deckGenerator = new CardDeck();
        deck = deckGenerator.getDeck();
        assertEquals(84, deck.size());
    }

    @Test
    public void shouldReturnPriorityWithStepOf10() {
        deck = new CardDeck();
        ArrayList<Integer> allowedPriorities = new ArrayList<>();
        for (int i = 80; i <= 420; i+= 10) {
            allowedPriorities.add(i);
        }
        int priority = deck.generatePriority(80, 420, 10);
        assertTrue(allowedPriorities.contains(priority));
    }

    @Test
    public void shouldReturnPriorityWithStepOf20() {
        deck = new CardDeck();
        ArrayList<Integer> allowedPriorities = new ArrayList<>();
        for (int i = 70; i <= 410; i+= 20) {
            allowedPriorities.add(i);
        }
        int priority = deck.generatePriority(70, 410, 20);
        assertTrue(allowedPriorities.contains(priority));
    }
}