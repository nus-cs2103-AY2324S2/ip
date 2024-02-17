package capone.ui.gui;

import javafx.application.Application;


/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Launches the JavaFX application by starting the Main class.
     *
     * @param args The command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
