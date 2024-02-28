package bmo;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Main method to start the application.
     *
     * @param args The arguments to start the application with.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}

