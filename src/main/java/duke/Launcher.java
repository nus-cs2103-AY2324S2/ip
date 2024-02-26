package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 * This class is used to launch the JavaFX application.
 */
public class Launcher {
    /**
     * The main entry point for application.
     * This method is used to launch the JavaFX application.
     */
    public static void main(String[] args) {
        // Application.launch(Duke.class, args);
        Application.launch(Main.class, args);
    }
}