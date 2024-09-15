package jayne;

import jayne.task.TaskList;

/**
 * Represents the main class for the Jayne application.
 * This class encapsulates the initialization of core components
 * such as Ui, TaskList, and Storage, and contains the main program loop.
 * Already made better GUI
 * Already made better personality
 */
public class Jayne {
    private TaskList taskList;
    private Storage storage;
    /**
     * Constructs a new Jayne object.
     * Initializes the UI, storage, and task list components.
     *
     * @param filepath the path to the file where tasks are saved and loaded from.
     */
    public Jayne(String filepath) throws JayneException {
        assert filepath != null : "File cannot be null";
        this.storage = new Storage(filepath);
        this.taskList = new TaskList(storage);
    }
    /**
     * Processes a user's input command, executes it, and returns a formatted response message.
     * It checks for empty input, throwing a {@link JayneException} if found. On successful command
     * execution, it prepends a customized greeting to the response. If the input indicates the end
     * of the session, it updates the application state accordingly. Errors during processing are
     * caught and returned as formatted error messages.
     *
     * @param input The user's command as a string.
     * @return A greeting followed by the command's execution result, or an error message.
     */
    public String run(String input) {
        assert input != null : "Input cannot be null";
        assert taskList != null : "TaskList cannot be null";
        Parser parser = new Parser(taskList);
        String msg = "";
        try {
            if (input.isEmpty()) {
                throw new JayneException("Input cannot be empty.");
            }
            msg = parser.parse(input);
            return "Hey, Snowieeee, " + msg;
        } catch (JayneException e) {
            return "Huh?!?!? " + e.getMessage() + "\n";
        }
    }
}
