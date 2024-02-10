package roland;

import java.util.Scanner;

/**
 * The Ui class represents the user interface for the Roland task management application.
 * It provides methods for displaying messages, reading user input, and showing errors.
 *
 * @author wolffe88
 */

public class Ui {

    private final String bot = "[ROLAND ⌐■-■] ";
    private final Scanner sc = new Scanner(System.in);

    /**
     * Displays the application logo and welcome message.
     */
    public void boot() {
        this.spacer();
        String logo = "██████╗  ██████╗ ██╗      █████╗ ███╗   ██╗██████╗\n"
                + "██╔══██╗██╔═══██╗██║     ██╔══██╗████╗  ██║██╔══██╗\n"
                + "██████╔╝██║   ██║██║     ███████║██╔██╗ ██║██║  ██║\n"
                + "██╔══██╗██║   ██║██║     ██╔══██║██║╚██╗██║██║  ██║\n"
                + "██║  ██║╚██████╔╝███████╗██║  ██║██║ ╚████║██████╔╝\n"
                + "╚═╝  ╚═╝ ╚═════╝ ╚══════╝╚═╝  ╚═╝╚═╝  ╚═══╝╚═════╝\n";
        System.out.println(logo);

        System.out.println(bot + "Hello! I am ROLAND");
        System.out.println(bot + "What can I do for you?");
        this.spacer();
    }

    /**
     * Prints a spacer line to the console for formatting purposes.
     */
    public void spacer() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    /**
     * Reads a line of user input from the console.
     *
     * @return The user input as a String.
     */
    public String readCommand() {

        return sc.nextLine();
    }

    /**
     * Displays an error message to the console with the bot identifier.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println(bot + message);
    }

    /**
     * Retrieves the bot identifier used in messages.
     *
     * @return The bot identifier.
     */
    public String getBot() {
        return this.bot;
    }

    /**
     * Displays a loading error message to the console.
     */
    public void showLoadingError() {
        System.out.println("Let's get started with some tasks shall we?");
    }
}
