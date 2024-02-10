package duke.main;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    
    /**
     * Main method to launch the Duke JavaFX application.
     *
     * @param args Command-line arguments passed to the application. Not used directly by Duke.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}

