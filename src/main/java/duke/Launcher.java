package duke;

import javafx.application.Application;

/**
 * The Launcher class is responsible for launching the Duke application.
 * It initializes and starts the JavaFX Application with the specified main class (Main).
 */
public class Launcher {
    /**
     * Main method to launch the Duke application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
