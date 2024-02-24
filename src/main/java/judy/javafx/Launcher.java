package judy.javafx;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Main method to run Judy application.
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
