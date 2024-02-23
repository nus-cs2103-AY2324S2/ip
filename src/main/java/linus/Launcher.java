package linus;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * main method for starting the javaFX for Linus.
     * @param args
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}