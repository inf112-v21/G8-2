package sid.roborally.game_mechanics.card;

/**
 * <h3>StepCard</h3>
 * <p>Contains information telling robot how it should move (forward or backward).</p>
 * <p>Steps is the magnitude of the movement.</p>
 *
 * @author Emil Eldøen
 */
public class StepCard extends Card{
    /* Magnitude of card-movement (how many times to repeat it)*/
    private int steps;

    public StepCard(int pri, int step, CardAction action) {
        super(pri, action);
        this.steps = step;
    }

    //Fetch the steps from the card itself
    public int getSteps() {
        return steps;
    }

    public String getCardDescription() { return steps + " " + getName(); }
}
