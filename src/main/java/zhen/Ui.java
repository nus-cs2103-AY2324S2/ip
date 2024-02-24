package zhen;

import java.util.Scanner;

/**
 * Represents the user interface of the application.
 * It is responsible for getting inputs from users and displaying message to users.
 */
public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Shows welcome to the users
     */
    public static String showWelcome() {
        String welcomeMessage = "Hello! I'm Zhen\nWhat can I do for you? ";
        print_message(welcomeMessage);
        return welcomeMessage;
    }

    /**
     * Shows exception to the users.
     */
    public static String showException(Exception e) {
        String exceptionMessage = print_message(e.getMessage());
        return e.getMessage();
    }

    /**
     * Organizes content to be displayed to users in a formatted way.
     *
     * @param messageToShow The message the program wants to show to the user.
     */
    public static String print_message(String messageToShow) {
        System.out.println("\n ----------------------------------");
        System.out.println(" " + messageToShow);
        System.out.println("\n ----------------------------------");
        return "\n ----------------------------------"
                + "\n " + messageToShow
                + "\n ----------------------------------";
    }

    /**
     * Reads inputs from users.
     *
     * @return The line of string that the user input.
     */
    public String readCommand() {
        return scanner.nextLine();
    }
}
