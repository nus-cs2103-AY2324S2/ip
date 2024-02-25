package georgie;

import javafx.application.Application;

/**
 * Represents a launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Launches the JavaFX application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}


