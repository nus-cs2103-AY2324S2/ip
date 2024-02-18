package squid.ui;

import javafx.application.Application;
import squid.Main;

/**
 * A launcher class to workaround classpath issues.
 * Adapted from <a href="https://se-education.org/guides/tutorials/javaFxPart1.html">SE-EDU</a>
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
