package common;

import java.util.Scanner;

/**
 * Represents the user interface that interacts with the user.
 */
public class Ui {
    private Scanner scanner = new Scanner(System.in);

    /**
     * Reads the user input.
     */
    public String readCommand() {
        return scanner.nextLine().strip();
    }

    /**
     * Shows the start up message upon successful loading of the program.
     */
    public String showWelcome() {
        String welcomeMessage = "Good Morning, Hustler! I'm Xavier.\n"
                + "What can I do for you?";
        System.out.println(welcomeMessage);
        showLine();

        return welcomeMessage;
    }

    /**
     * Shows the separating line for different messages.
     */
    public static void showLine() {
        String line = "________________________________________\n";
        System.out.println(line);
    }
}
