package luke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 * Taken from https://se-education.org/guides/tutorials/javaFxPart1.html
 * For some reason the jar file refuses to work without this and gives
 * Error: JavaFX runtime components are missing, and are required to run this application
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Luke.class, args);
    }
}