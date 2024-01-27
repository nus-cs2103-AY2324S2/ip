public class Ui {
    // Configuration to reset output color to System.out.
    private static final String ANSI_RESET = "\u001B[0m";
    // Configuration for red colored outputs to System.out.
    private static final String ANSI_RED = "\u001B[31m";

    /**
     * Prints divider line.
     */
    public void showLine() {
        System.out.println("=".repeat(80));
    }

    /**
     * Prints message.
     *
     * @param message Message to be printed to output.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints error message.
     *
     * @param errorMessage Error message to be printed to output.
     */
    public void showError(String errorMessage) {
        System.out.println(ANSI_RED + errorMessage + ANSI_RESET);
    }

    /**
     * Prints greeting message with divider lines.
     */
    public void showGreeting() {
        this.showLine();
        this.showMessage("Hello, I'm Ted. What can I do for you today?");
        this.showLine();
    }

    /**
     * Prints farewell message.
     */
    public void showFarewell() {
        this.showMessage("Bye. See you again.");
    }
}
