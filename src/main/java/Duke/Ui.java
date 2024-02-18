package Duke;

import java.util.Scanner;

/**
 * Representing the user interface of the application.
 * It is responsible for getting inputs from users and displaying message to users.
 */
public class Ui {
    private final Scanner scanner = new Scanner(System.in);
    private static final String logo = "ZZZZZ   H   H  EEEEE  N   N\n"
            + "   Z    H   H  E      NN  N\n"
            + "  Z     HHHHH  EEEE   N N N\n"
            + " Z      H   H  E      N  NN\n"
            + "ZZZZZ   H   H  EEEEE  N   N\n";

    /**
     * Show welcome to the users
     */
    public static String showWelcome() {
        String welcomeMessage = "";
        System.out.println("Hello from\n" + logo);
        welcomeMessage = welcomeMessage + "Hello from\n" + logo;
        welcomeMessage = welcomeMessage
                + "\n"
                + print_message("Hello! I'm ZHEN\n What can I do for you? ");
        return welcomeMessage;
    }

    public static String showException(Exception e) {
        String exceptionMessage = print_message(e.getMessage());
        return e.getMessage();
    }

    /**
     * Organize content to be displayed to users in a formatted way.
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
     * Reading inputs from users.
     *
     * @return The line of string that the user input.
     */
    public String readCommand() {
        return scanner.nextLine();
    }
}
