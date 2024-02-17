package nicky;

import javafx.application.Application;

/**
 * Launcher class serves as an entry point to the Nicky application, providing a workaround
 * for classpath issues typically encountered when launching a JavaFX application.
 */
public class Launcher {
    /**
     * Runs the Nicky application.
     *
     * @param args Arguments passed to the application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
