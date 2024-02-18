package micromanager.logic;

import javafx.application.Platform;
import micromanager.commands.Command;
import micromanager.dataprocessing.CommandParser;
import micromanager.exceptions.DukeException;
import micromanager.storage.Storage;
import micromanager.storage.TaskList;

/**
 * Duke is a task management application that allows users to manage their tasks through a command-line interface.
 * Users can add, delete, mark as done, and list tasks. Tasks are saved to a file for persistence between sessions.
 * Duke also provides error handling and a user-friendly interface.
 */
public class Logic {
    private static final String SAVE_FILE = "./data/tasks.txt";
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructs a Duke object with the specified file path for task storage.
     */
    public Logic() {
        try {
            storage = new Storage(SAVE_FILE);
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            taskList = new TaskList();
        }
    }

    public String getResponse(String input) {
        assert input != null : "There should be an input";
        try {
            Command inputCommand = CommandParser.parse(input);
            if (inputCommand.isExit()) {
                Platform.exit();
            }
            String consoleText = inputCommand.execute(taskList, storage);
            storage.update(taskList);

            return consoleText;
        } catch (DukeException e) {
            return "Error occurred executing command: " + e.getMessage();
        }
    }
}
