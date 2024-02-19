package hal.gui;

import hal.command.Command;
import hal.task.Storage;
import hal.task.TaskList;

import java.io.FileNotFoundException;

/**
 * The Main class initializes and runs the Duke application.
 */
public class Hal {

    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;
    private final String FILE_PATH = "data/hal.txt";
    private boolean isExit = false;

    /**
     * Constructs a Main object with the specified file path.
     *
     */
    public Hal() {

        taskList = new TaskList();
        ui = new Ui(taskList);
        storage = new Storage(FILE_PATH, taskList);
        try {
            taskList.initialisePrevTaskList(storage.readFromFile());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        // tasks added to tasklist already
        Command command = ui.getCommand(input);

        if (command.isExit()) {
            isExit = true;
        }

        // then we store it
        storage.writeToFile(FILE_PATH);

        return command.execute(taskList);
    }

    static String getWelcomeMessage() {
        return "Good Afternoon Gentlemen. How may I assist you.";
    }

    public boolean isExit() {
        return isExit;
    }
}
