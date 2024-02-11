package ukecat;

/**
 * The main class representing the UkeCat application.
 * UkeCat is a simple program that interacts with users to manage tasks.
 * This class initializes the user interface, loads tasks from storage,
 * and handles user input through the Parser class.
 */
public class UkeCat {
    public final Ui ui;

    public UkeCat() {
        ui = new Ui();
        FileManager.loadTasks();
    }

    /**
     * Returns the same input string as the response, to be displayed on GUI.
     *
     * @param input The reply string.
     * @return The response generated based on the reply.
     */
    public String getResponse(String input) {
        return input;
    }
}
