package commands;

import exceptions.InvalidFormatException;
import exceptions.LeluException;
import tasks.ToDo;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * This class represents a command for adding a todo task to the task list
 * managed by the chatbot. When executed, this command parses user input to extract
 * the todo task details and adds it to the task list.
 */
public class AddToDoCommand extends Command {

    private static final String COMMAND = "todo ";

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
    public String execute(TaskList tasks, Ui ui, Storage storage, String message) throws LeluException {
        checkEmptyDescription(message, COMMAND, LeluException.ErrorType.TODO);
        String s = message.replaceFirst(COMMAND, "");
        assert message.length() > COMMAND.length() : "Input not handled properly";
        return tasks.addTask(new ToDo(s));
    }
}
