package paimon;

import paimon.command.Command;
import paimon.task.Task;
import paimon.task.TaskList;
import paimon.util.CommandParser;
import paimon.util.FileHandler;
import paimon.util.UiHandler;


/**
 * Serves as the entry point for the application.
 * It initializes the user interface and the task list, then enters a loop
 * to read and execute commands until an exit command is received.
 */
public class DialogHandler {

    private TaskList taskList;

    /**
     * Constructs a DialogHandler Object
     */
    public DialogHandler() {
        // TODO Implement Exception Catching here
        this.taskList = FileHandler.loadTaskList();
    }
    /**
     * Gets the response from Paimon
     *
     * @param input The input command.
     * @return The response from Duke.
     */
    public String getResponse(String input) {
        try {
            CommandParser parser = new CommandParser(input);
            Command command = parser.parseInput();
            return command.execute(taskList);
        } catch (ChatException e) {
            return UiHandler.getErrorMessage(e);
        }
    }

}
