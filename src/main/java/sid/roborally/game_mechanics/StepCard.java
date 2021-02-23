package sid.roborally.game_mechanics;

/**
 * This is the step card
 * It inherits priority from the Card class
 * It also has a number of steps it will move a robot
 * This is acquired via the getSteps() method
 *
 * @author Emil Eldøen
 */
public class StepCard extends Card{
    //The steps the card would move a robot
    private int steps;

    //Constructor giving the card priority and steps
    public StepCard(int pri, int step) {
        //Construct card with priority
        super(pri);
        this.steps = step;
    }

    //Fetch the steps from the card itself
    public int getSteps() {
        return steps;
    }
}
