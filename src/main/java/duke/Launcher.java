package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Main method to launch the Duke program.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Application.launch(Duke.class, args);
    }
}
