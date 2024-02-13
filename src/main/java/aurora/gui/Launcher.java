package aurora.gui;

import javafx.application.Application;

/**
 * Entry point for the application to avoid class path problems.
 * Code reused and adapted from: https://se-education.org/guides/tutorials/javaFxPart4.html
 */
public class Launcher {

    /**
     * Main function to run the entire application
     * @param args
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
