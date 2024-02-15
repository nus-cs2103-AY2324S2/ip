package BotChat;

/**
 * Represents the User Interface (UI) for the botChat application.
 */
public class Ui {

    /**
     * Displays the goodbye message when the user exits the botChat application.
     */
    public void showGoodbyeMessage() {
        System.out.println("____________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showErrorMessage(String message) {
        System.out.println("____________________________________________________________\n"
                + message + "\n"
                + "____________________________________________________________\n");
    }
}
