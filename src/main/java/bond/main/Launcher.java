package bond.main;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 *
 * @version 0.2
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
