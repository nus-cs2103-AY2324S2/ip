package iggly.javafx;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Serves as the entry point for the Java application.
     * It launches the JavaFX application specified by the Main class.
     *
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
