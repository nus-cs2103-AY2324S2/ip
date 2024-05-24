package Duke.duke;

import Duke.UI.MainWindow;

/**
 * The main class for the Duke application.
 */
public class Duke {

    /**
     * Default constructor for the Duke class.
     */
    public Duke() {
    }

    /**
     * Runs the Duke application.
     */
    private void runApp() {
        boolean isTerminated = false;

        // Continue running the application until termination signal is received
        while (!isTerminated) {
            isTerminated = MainWindow.isTerminated();
        }
    }

    /**
     * The main method that initializes and runs the Duke application.
     *
     * @param args Command-line arguments (not used in this case).
     */
    public static void main(String[] args) {
        new Duke().runApp();
    }
}



