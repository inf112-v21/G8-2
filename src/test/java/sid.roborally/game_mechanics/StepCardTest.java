package sid.roborally.game_mechanics;
import org.hamcrest.Condition;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
public class StepCardTest {
    StepCard movementCard = new StepCard(100,3);
    @Test
    public void cardHasSteps(){
        assertEquals(movementCard.getSteps(),3);
    }

    @Test
    public void cardHasPriority(){
        assertEquals(movementCard.getPriority(),100);
    }
}
