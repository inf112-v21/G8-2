package sid.roborally.game_mechanics;

import org.junit.Test;
import sid.roborally.game_mechanics.card.Card;
import sid.roborally.game_mechanics.card.CardAction;

import static org.junit.Assert.assertEquals;

public class CardTest {
    private class CardInheriter extends Card {
        public CardInheriter(int pri, CardAction action)
        {super(pri, action);}
    }

    @Test
    public void checkCardPriority(){
        Card priorityCard = new CardInheriter(100, null);
        assertEquals(100,priorityCard.getPriority());
    }
}
