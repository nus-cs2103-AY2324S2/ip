package commands;

import exceptions.InvalidFormatException;
import exceptions.LeluException;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;
import tasksstorage.Storage;
import tasksstorage.TaskList;
import ui.Ui;

/**
 * This class represents a command for adding a todo task to the task list
 * managed by the chatbot. When executed, this command parses user input to extract
 * the todo task details and adds it to the task list.
 */
public class AddToDoCommand extends Command {

    /**
     * Executes the command to add a task to the list of recorded tasks.
     *
     * @param tasks Recorded list of tasks.
     * @param ui Format of output shown.
     * @param storage To save and load tasks recorded.
     * @param message Input of user.
     * @throws LeluException If the input is invalid or with the wrong format.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, String message) throws LeluException {
        if (message.replaceAll(" ", "").equals("todo")) {
            InvalidFormatException.callInvalidFormatException(LeluException.ErrorType.TODO);
        }
        String s = message.replaceFirst("todo ", "");
        tasks.addTask(new ToDo(s));
    }
}
