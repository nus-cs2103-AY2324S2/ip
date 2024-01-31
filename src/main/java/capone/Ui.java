package capone;

/**
 * The Ui class handles user interface-related operations.
 * It includes methods for printing welcome messages and
 * sending messages to the user.
 *
 * @author Tay Rui-Jie
 */
public class Ui {

    /**
     * Prints a welcome message with the Capone logo.
     */
    public void printWelcomeMsg() {
        String logo = "░█▀▀░█▀█░█▀█░█▀█░█▀█░█▀▀░\n"
                + "░█░░░█▀█░█▀▀░█░█░█░█░█▀▀░\n"
                + "░▀▀▀░▀░▀░▀░░░▀▀▀░▀░▀░▀▀▀░";
        System.out.printf("Hello! I'm\n%s\nWhat can I do for you?\n%n", logo);
    }

    /**
     * Sends a message to the user.
     *
     * @param message The message to be sent.
     */
    public void sendMessage(String message) {
        System.out.printf(message);
    }
}
