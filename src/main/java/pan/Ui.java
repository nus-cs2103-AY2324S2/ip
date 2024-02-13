package pan;

/**
 * Ui - Represents the Ui Class that manages the Ui String operations for the user such as hi and bye.
 * @author Jerome Goh
 */
public class Ui {
    /**
     * Creates an Ui instance.
     */
    public Ui() {}

    /**
     * Prints out the Welcome Message to the user.
     */
    public String hello() {
        return "Hello! I'm Pan\nWhat can I do for you?";
    }

    /**
     * Prints out the Farewell Message to the user.
     */
    public String bye() {
        return "Bye. Hope to see you again soon!";
    }
}
