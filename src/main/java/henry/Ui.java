package henry;

import java.util.Scanner;

/**
 * Represents the user interface of the application.
 */
public class Ui {
    Scanner scanner;

    /**
     * Creates an Ui object.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads user input.
     *
     * @return The input from the user.
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Greets the user.
     */
    public void greet() {
        String logo = "  _    _                       \n" +
                " | |  | |                      \n" +
                " | |__| | ___ _ __  _ __ _   _ \n" +
                " |  __  |/ _ \\ '_ \\| '__| | | |\n" +
                " | |  | |  __/ | | | |  | |_| |\n" +
                " |_|  |_|\\___|_| |_|_|   \\__, |\n" +
                "                          __/ |\n" +
                "                         |___/ \n";
        String greetMessage = "Hello! I'm Henry\nWhat can I do for you?";
        System.out.println(logo);
        System.out.println(greetMessage);
        System.out.println();
    }

    /**
     * Shows the error message.
     *
     * @param err The error message.
     */
    public void showError(String err) {
        System.err.println(err);
    }

    /**
     * Shows the error message when error loading file.
     */
    public void showLoadingError() {
        System.err.println("Error reading tasks from file!\nWill recreate file!");
    }
}
