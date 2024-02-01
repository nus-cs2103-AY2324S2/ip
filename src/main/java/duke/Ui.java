package duke;

/**
 * Ui Class is responsible for interacting with the user.
 */
public class Ui {
    private String name = "XVX-016 Aerial";

    /**
     * Empty Constructor for Ui
     */
    public Ui() {
    }

    /**
     * Method that welcomes the user upon launching Duke.
     */
    public void greeting() {
        System.out.println("\tHELLO, Nice to meet you. I am " + this.name + "!");
        System.out.println("\tWhat are we doing today?");
    }

    /**
     * Method that bids farewell to the user upon terminating Duke.
     */
    public void bye() {
        System.out.println("\tSee you next time! ♥( ˆ⌣ ˆԅ)");
    }
}
