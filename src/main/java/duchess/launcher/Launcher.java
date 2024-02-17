package duchess.launcher;

import duchess.Main;
import javafx.application.Application;

/**
 * This package provides a launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Launches the Duchess application.
     *
     * @param args The command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
