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
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return input;
    }
}
