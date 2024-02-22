package tes;

import javafx.application.Application;

// Solution below adapted from https://se-education.org/guides/tutorials/javaFx.html
/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
