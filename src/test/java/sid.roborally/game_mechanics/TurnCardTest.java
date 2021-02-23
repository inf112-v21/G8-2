package sid.roborally.game_mechanics;

import org.junit.Test;
import sid.roborally.game_mechanics.card.TurnCard;

import static org.junit.Assert.assertEquals;

public class TurnCardTest {

    TurnCard card = new TurnCard(600,"left");

    @Test
    public void TurnCardTurnsLeftTest() {
        assertEquals(card.getOrientation(),Direction.WEST);
    }
    @Test
    public void TurnCardTurnsRightTest() {
        card = new TurnCard(600, "right");
        assertEquals(card.getOrientation(),Direction.EAST);
    }

    @Test
    public void TurnCardTurnsArundTest() {
        card = new TurnCard(600, "around");
        assertEquals(card.getOrientation(),Direction.SOUTH);
    }



}