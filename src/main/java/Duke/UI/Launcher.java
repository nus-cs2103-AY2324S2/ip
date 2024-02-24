package Duke.UI;

import javafx.application.Application;

/**
 * The {@code Launcher} class is responsible for launching the JavaFX GUI for the user interface.
 */
public class Launcher {

    /**
     * The main method that launches the JavaFX GUI by starting the {@link MainWindow} class.
     *
     * @param args Command-line arguments (not used in this case).
     */
    public static void main(String[] args) {
        Application.launch(MainWindow.class, args);
    }
}
