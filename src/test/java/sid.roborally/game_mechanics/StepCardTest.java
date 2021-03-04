package sid.roborally.game_mechanics;
import org.junit.Test;
import sid.roborally.game_mechanics.card.CardAction;
import sid.roborally.game_mechanics.card.StepCard;

import static org.junit.Assert.assertEquals;
public class StepCardTest {
    StepCard movementCard = new StepCard(100,3, CardAction.FORWARD);
    @Test
    public void cardHasSteps(){
        assertEquals(movementCard.getSteps(),3);
    }

    @Test
    public void cardHasPriority(){
        assertEquals(movementCard.getPriority(),100);
    }
}
