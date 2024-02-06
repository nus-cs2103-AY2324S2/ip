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
    public String greeting() {
        return "HELLO, Nice to meet you. I am " + this.name + "!";
    }

    /**
     * Method that bids farewell to the user upon terminating Duke.
     */
    public void bye() {
        System.out.println("\tSee you next time!");
    }
}
