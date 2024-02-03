package tiny.gui;

import javafx.application.Application;

import tiny.Tiny;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Tiny.class, args);
    }
}