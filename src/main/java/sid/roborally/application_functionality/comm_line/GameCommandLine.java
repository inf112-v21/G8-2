package sid.roborally.application_functionality.comm_line;

import sid.roborally.game_mechanics.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameCommandLine {

    private static int CARD_AMOUNT = 5;

    /**
     * <p>Gets the max number of input and returns the first valid number given.</p>
     * @param optionsNum
     * @return int - ZERO if no valid input was given (shouldn't ever happen).
     */
    private static int getValidInput(int optionsNum) {
        Scanner sc = new Scanner(System.in);
        int optionChosen;
        while(sc.hasNextInt())
        {
            optionChosen = sc.nextInt();
            if(optionChosen > 0 && optionChosen <= optionsNum)
                return optionChosen;
            else System.out.println("\nPlease give a valid input...\n");
        }
        return 0;
    }

    /**
     * <p>When called from game/gamerunner this method will spawn a promt in command-line
     *    asking the user to select their card-sequence.</p>
     * @param givenCards
     * @return
     */
    public static ArrayList<Card> getLocalCardSequenceInput(List<Card> givenCards) {
        System.out.println("Your cards are: ");
        for(int i = 0; i < givenCards.size(); i++)
            System.out.println(Integer.toString(i+1) + ". " + givenCards.get(i).getName());

        /* Will use this list to get selected indices and check for duplicates */
        ArrayList<Integer> retCardsIndices = new ArrayList<>();

        System.out.println("\nPlease type in the cards you want in the order you want them:\n");

        for(int i = 0; i < CARD_AMOUNT; i++) {
            int input = getValidInput(CARD_AMOUNT) - 1; //Letting user select from 1 instead of 0.
            while(retCardsIndices.contains(input)) {
                System.out.println("Card already selected.");
                input = getValidInput(CARD_AMOUNT) - 1;
            }
            retCardsIndices.add(input);
        }

        /* Use selected indices to select cards */
        ArrayList<Card> selectedCards = new ArrayList<>();
        for(Integer i : retCardsIndices)
            selectedCards.add(givenCards.get(i));

        return selectedCards;
    }
}
