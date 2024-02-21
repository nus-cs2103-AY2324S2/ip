package chad.gui;

import chad.Main;
import javafx.application.Application;

/**
 * A Launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
