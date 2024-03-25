package duke;

import javafx.application.Application;

/**
 * The Launcher class serves as a workaround to classpath issues when launching a JavaFX application.
 * This class specifically acts as the entry point to the application, invoking the JavaFX Application's
 * launch method with the Main class as an argument. This approach facilitates the proper handling of
 * JavaFX runtime components and resources, ensuring the application starts smoothly without encountering
 * classpath-related errors.
 *
 * Use this class to start the Duke application when running from environments where direct execution
 * of the JavaFX Application class may lead to classpath or module path issues.
 */
public class Launcher {
    /**
     * The main method that serves as the entry point of the application.
     * It delegates the application startup to the JavaFX framework by calling
     * Application.launch with the Main class specified, which initiates the JavaFX
     * application lifecycle.
     *
     * @param args Command line arguments passed to the application. These are not
     *             used directly by the Launcher but are forwarded to the JavaFX
     *             application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
