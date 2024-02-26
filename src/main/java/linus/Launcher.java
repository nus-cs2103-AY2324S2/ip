package linus;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Main method for starting the javaFX for Linus.
     * @param args An array of arguments.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}