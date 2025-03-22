package duke.main;

import javafx.application.Application;

/**
 * The Launcher class provides the entry point for launching a JavaFX application.
 * This class contains the main method that invokes the launch() method of the Application class.
 */
public class Launcher {
    /**
     * The main method serves as the entry point of the program.
     * It launches the JavaFX application by invoking the launch() method of the Application class.
     *
     * @param args The command-line arguments passed to the program.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
