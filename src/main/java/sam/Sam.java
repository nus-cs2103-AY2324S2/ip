package sam;

import java.io.IOException;

import sam.command.Command;

/**
 * The main class of the Sam application.
 *
 * This class serves as the entry point for Sam. It contains the main method
 * where the application starts its execution.
 */
public class Sam {
    private Storage storage;
    private TaskList tasks;
    /**
     * Constructs a new Sam object.
     *
     * Initializes the user interface, storage, and taskList.
     * If loading tasks from the specified file path fails due to IOException or SamException,
     * displays a loading error message and initializes an empty task list.
     *
     * @param filePath the file path from which to load tasks
     */
    public Sam(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException | SamException e) {
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            if (command.isExit()) {
                System.exit(0);
            }
            return command.execute(tasks, storage);
        } catch (SamException e) {
            return e.getMessage();
        }
    }
}
