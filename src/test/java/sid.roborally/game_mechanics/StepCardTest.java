package sid.roborally.game_mechanics;
import org.hamcrest.Condition;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
public class StepCardTest {
    @Test
    public void cardHasSteps(){
        StepCard movementCard = new StepCard(100,3);
        assertEquals(movementCard.getSteps(),3);
    }

    @Test
    public void cardHasPriority(){
        StepCard movementCard = new StepCard(100,3);
        assertEquals(movementCard.getPriority(),100);
    }
}
