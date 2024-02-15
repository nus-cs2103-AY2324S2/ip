package friday.main;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * The main method to launch the JavaFX application.
     * @param args The command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        assert args != null : "Command-line arguments must not be null";
        assert args.length == 0 : "Command-line arguments must be empty";

        Application.launch(Main.class, args);
    }
}
