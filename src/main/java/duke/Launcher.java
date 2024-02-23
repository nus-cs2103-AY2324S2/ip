package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Main method to launch the application.
     *
     * @param args an array of command-line arguments for the application (not used here)
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
