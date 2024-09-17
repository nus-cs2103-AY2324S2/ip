package duke;

import javafx.application.Application;

/**
 * Launcher class to workaround classpath issues when launching a JavaFX application.
 * This class serves as the main entry point for the application. It is separated from the Main class
 * which extends Application to avoid module-path issues with JavaFX.
 */
public class Launcher {
    /**
     * Main method to launch the application.
     * @param args Command line arguments passed to the application.
     */
    public static void main(String[] args) {
        // Launch the JavaFX application. The application's start method will be called.
        Application.launch(Main.class, args);
    }
}
