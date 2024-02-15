package duke.kbot;

import javafx.application.Application;

/**
 * Entry point to the program JavaFx.
 */
public class Launcher {
    public static void main(String[] args) {
        TaskManager.loadLocalSavedTasks(); // checking if there are local files to load
        Application.launch(KBot.class, args);
    }
}
