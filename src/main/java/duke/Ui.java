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
    public String bye() {
        return "See you next time! Close the tab to terminate!";
    }

    public String miscCommands(String command) {
        if (command.equals("date")) {
            return "Maybe another time ><\n";
        } else if (command.equals("whoami")) {
            return "You are ZGMF X10A Freedom!\n"
                    + "How could you forget? O_o\n";
        } else if (command.equals("hello")) {
            return "HELLO FREEDOM!! c:";
        } else {
            return "I do not understand";
        }
    }
}