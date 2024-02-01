package seedu.chatteroo.ui;

/**
 * Represents the user interface of the Chatteroo ChatBot program.
 */
public class Ui {
    /**
     * Constructor for the Ui class.
     */
    public Ui() {
    }

    /**
     * Shows the welcome text.
     */
    public void showWelcomeText() {
        System.out.println("Hello! I'm Chatteroo\n" + "What can I do for you?\n");
    }

    /**
     * Shows the exit text.
     */
    public void showByeText() {
        System.out.println("Chatteroo says bye and hopes to see you again soon!\n");
    }

    /**
     * Shows the specified text when the list contains no tasks.
     */
    public void showNoTaskText() {
        System.out.println("There are no tasks in the list.\n");
    }
}
