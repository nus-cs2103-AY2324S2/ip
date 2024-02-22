package teletubbi;

import java.util.Scanner;

/**
 * Represents the user interface for interacting with the chatbot.
 * This class handles input and output operations, providing methods for
 * reading user commands, displaying messages, and presenting a consistent
 * interface to the user.
 */
public class Ui {
    private Scanner scanner;
    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void breakLine() {
        System.out.println("---------------------------------------");
    }

    /**
     * Prints a greeting to the user.
     */
    public void greet() {
        breakLine();
        System.out.println("Hello! I'm teletubbi" + "\nWhat can I do for you?");
        breakLine();
    }

    /**
     * Prints a goodbye message to the user.
     */
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
