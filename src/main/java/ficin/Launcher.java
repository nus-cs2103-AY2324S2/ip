package ficin;

import javafx.application.Application;

/**
 * Serves as an entry point to launch the JavaFX application, avoiding classpath issues.
 *
 * This class is designed to circumvent common classpath issues such as:
 * - JavaFX classes not being found due to incorrect classpath configuration.
 *
 * Using this class helps ensure that the application starts smoothly across different
 * environments by initiating the JavaFX application in a separate class.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
