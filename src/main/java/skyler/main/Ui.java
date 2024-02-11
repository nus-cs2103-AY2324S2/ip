package skyler.main;

import java.util.Scanner;

public class Ui {

    /**
     * Returns a greeting message including the chatbot's name and an introductory
     * message.
     *
     * @param chatbotName The name of the chatbot.
     * @return A greeting message.
     */
    public static String getGreeting(String chatbotName) {
        StringBuilder greeting = new StringBuilder("   /\\_/\\\n");
        greeting.append("  ( o.o ) Hello! I'm ").append(chatbotName).append("\n");
        greeting.append("   > ^ < What can I do for you?\n");
        return greeting.toString();
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
        return userInput + "\n";
    }

    /**
     * Returns a farewell message when the user decides to exit the chat.
     *
     * @return A farewell message.
     */
    public static String getByeMessage() {
        return "Skyler: Bye. Hope to see you again soon!\n";
    }

    /**
     * Returns an error message with a customized prefix indicating it's from the
     * chatbot.
     *
     * @param message The error message to be returned.
     * @return An error message.
     */
    public static String getErrorMessage(String message) {
        return "Skyler: Woof, " + message + "\n";
    }
}
