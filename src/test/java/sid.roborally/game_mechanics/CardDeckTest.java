package sid.roborally.game_mechanics;

import org.junit.Test;
import sid.roborally.game_mechanics.card.Card;
import sid.roborally.game_mechanics.card.CardAction;
import sid.roborally.game_mechanics.card.CardDeck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import static org.junit.Assert.*;

public class CardDeckTest {

    CardDeck deck = new CardDeck();

    @Test
    public void deckHoldsAllCards() {
        Stack<Card> deck;
        CardDeck deckGenerator = new CardDeck();
        deck = deckGenerator.getDeckStack();
        assertEquals(84, deck.size());
    }

    @Test
    public void shouldReturnDifferentPriority() {
        ArrayList<Integer> cardPriority = new ArrayList<>();
        for (Card card : deck.getDeckStack()) {
            if (card.getAction().equals(CardAction.TURN_AROUND)){
                cardPriority.add(card.getPriority());
            }
        }

        Collections.sort(cardPriority);
        for (int i = 1; i < cardPriority.size(); i++) {
            assertFalse(cardPriority.get(i - 1).equals(cardPriority.get(i)));
        }
    }

    @Test
    public void shouldReturnPriorityWithStep() {
        ArrayList<Integer> cardPriority = new ArrayList<>();
        for (Card card : deck.getDeckStack()) {
            if (card.getAction().equals(CardAction.TURN_RIGHT)){
                cardPriority.add(card.getPriority());
            }
        }

        Collections.sort(cardPriority);
        assertTrue((cardPriority.get(0) + 20) == cardPriority.get(1));
    }

    @Test
    public void cardsRemovedWhenDealt(){
        Card removedCard = deck.getNextCard();
        assertFalse(deck.getDeckStack().contains(removedCard));
    }
}