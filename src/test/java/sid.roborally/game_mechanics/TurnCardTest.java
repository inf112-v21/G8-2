package sid.roborally.game_mechanics;

import org.junit.Test;
import sid.roborally.game_mechanics.card.CardAction;
import sid.roborally.game_mechanics.card.TurnCard;

import static org.junit.Assert.assertEquals;

public class TurnCardTest {

    TurnCard card = new TurnCard(600,CardAction.TURN_LEFT);

    @Test
    public void TurnCardReturnsLeftTest() {
        assertEquals(card.getTurnDirection(),"Turn Left");
    }
    @Test
    public void TurnCardReturnsRightTest() {
        card = new TurnCard(600, CardAction.TURN_RIGHT);
        assertEquals(card.getTurnDirection(),"Turn Right");
    }

    @Test
    public void TurnCardReturnsAroundTest() {
        card = new TurnCard(600, CardAction.TURN_AROUND);
        assertEquals(card.getTurnDirection(),"Turn Around");
    }



}