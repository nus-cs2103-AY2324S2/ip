package kai.ui;

public class Ui {
    /**
     * Displays welcome message when chatbot is first booted up.
     */
    public static void showWelcome() {
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
    public static void showFarewell() {
        System.out.println("Bye Bye. Hope to see you again soon!");
    }
}
