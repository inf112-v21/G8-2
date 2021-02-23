package sid.roborally.game_mechanics;

import org.junit.Test;
import sid.roborally.game_mechanics.card.Card;

import static org.junit.Assert.assertEquals;

public class CardTest {
    @Test
    public void checkCardPriority(){
        Card priorityCard = new Card(100);
        assertEquals(100,priorityCard.getPriority());

    }
}
