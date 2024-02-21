package aurora.gui;

import javafx.application.Application;

/**
 * The Launcher class is the entry point for the application to avoid class path problems.
 * Code reused and adapted from: https://se-education.org/guides/tutorials/javaFxPart4.html
 */
public class Launcher {

    /** Runs the entire application. */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
