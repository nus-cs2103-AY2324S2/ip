package duke.util;

import java.util.Scanner;

public class Ui {
    private Scanner sc;

    /**
     * Creates a new Ui object, initialize scanner object.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Reads new input by user and store it in a String variable.
     *
     * @return input by user.
     */
    public String readUserInput() {
        return sc.nextLine();
    }

    /**
     * Displays welcome message when chatbot is first booted up.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm KAI\n" + "Please type in what you want to do");
    }

    /**
     * Shows error message when exception is caught.
     *
     * @param message Exception error message.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Displays farewell messages when chatbot is terminated.
     */
    public void showFarewell() {
        sc.close();
        System.out.println("Bye Bye. Hope to see you again soon!");
    }
}
