package duke.kbot;

import javafx.application.Application;

/**
 * Entry point to the program JavaFx.
 * 
 * @author: CHEN WENLONG
 * @version: CS2103T AY23/24 Semester 2
 */
public class Launcher {
    public static void main(String[] args) {
        TaskManager.loadLocalSavedTasks(); // checking if there are local files to load
        Application.launch(KBot.class, args);
    }
}
