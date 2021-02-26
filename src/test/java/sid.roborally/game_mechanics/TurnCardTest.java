package sid.roborally.game_mechanics;

import org.junit.Test;
import sid.roborally.game_mechanics.card.TurnCard;

import static org.junit.Assert.assertEquals;

public class TurnCardTest {

    TurnCard card = new TurnCard(600,"left");

    @Test
    public void TurnCardReturnsLeftTest() {
        assertEquals(card.getTurnDirection(),"left");
    }
    @Test
    public void TurnCardReturnsRightTest() {
        card = new TurnCard(600, "right");
        assertEquals(card.getTurnDirection(),"right");
    }

    @Test
    public void TurnCardReturnsAroundTest() {
        card = new TurnCard(600, "around");
        assertEquals(card.getTurnDirection(),"around");
    }



}