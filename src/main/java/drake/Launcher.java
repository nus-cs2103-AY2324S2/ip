package drake;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Main method that serves as the entry point for the JavaFX application.
     *
     * @param args Command-line arguments passed to the application. These arguments
     *             are forwarded to the JavaFX application class during launch.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
