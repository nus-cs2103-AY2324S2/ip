package paimon;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import paimon.command.Command;
import paimon.task.TaskList;
import paimon.util.CommandParser;
import paimon.util.FileHandler;
import paimon.util.UiHandler;

/**
 * Serves as the entry point for the application.
 * It initializes the user interface and the task list, then enters a loop
 * to read and execute commands until an exit command is received.
 */
public class Paimon extends Application {
    /**
     * The main method that starts the application.
     * It performs initial setup by greeting the user, loading the task list from a file,
     * and then enters a loop to process user commands until an exit command is issued.
     */
    public static void main(String[] args) {
        UiHandler ui = new UiHandler();
        ui.displayGreetMessage();
        TaskList taskList = FileHandler.loadTaskList();
        boolean isActive = true;
        while (isActive) {
            try {
                String userInput = ui.readCommand();
                CommandParser parser = new CommandParser(userInput);
                Command currentCommand = parser.parseInput();
                currentCommand.execute(taskList, ui);
                if (currentCommand.isExit()) {
                    isActive = false;
                }
            } catch (ChatException e) {
                ui.displayError(e);
            }
        }
        ui.displayExitMessage();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

}

