package skyler.main;
import java.util.Scanner;

public class Ui {
    private static final String LINE = "------------------------------------------------------------";

    /**
     * Prints a greeting message including the chatbot's name and an introductory
     * message.
     *
     * @param chatbotName The name of the chatbot.
     */
    public static void printGreeting(String chatbotName) {
        System.out.println("   /\\_/\\");
        System.out.println("  ( o.o ) Hello! I'm " + chatbotName);
        System.out.println("   > ^ < What can I do for you?");
        System.out.println(LINE);
    }

    /**
     * Prompts the user for input, reads the input from the provided scanner, and
     * returns the user's input.
     *
     * @param scanner The Scanner object used to read user input.
     * @return The user's input.
     */
    public static String getUserInput(Scanner scanner) {
        System.out.print("You: ");
        String userInput = scanner.nextLine();
        System.out.println(LINE);
        return userInput;
    }

    /**
     * Prints a farewell message when the user decides to exit the chat.
     */
    public static void printByeMessage() {
        System.out.println("Skyler: Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    /**
     * Prints an error message with a customized prefix indicating it's from the
     * chatbot.
     *
     * @param message The error message to be printed.
     */
    public static void printErrorMessage(String message) {
        System.out.println("Skyler: Woof, " + message);
        System.out.println(LINE);
    }
}
