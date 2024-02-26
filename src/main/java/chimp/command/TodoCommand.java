package chimp.command;

import chimp.core.Storage;
import chimp.core.TaskList;
import chimp.core.Ui;
import chimp.exception.CommandExecuteException;

/**
 * Represents a command to add a todo task.
 * Inherits from the Command class.
 */
public class TodoCommand extends Command {
    String text;

    /**
     * Constructs a TodoCommand object with the given text.
     *
     * @param text The description of the todo task.
     */
    public TodoCommand(String text) {
        this.text = text;
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return False, as the TodoCommand is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the TodoCommand by adding the todo task to the task list.
     *
     * @param tasks   The task list.
     * @param ui      The user interface.
     * @param storage The storage for saving tasks.
     * @return The message to be displayed to the user.
     * @throws CommandExecuteException If there is an error executing the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws CommandExecuteException {
        tasks.add(text);
        return ui.say(tasks.get(tasks.size() - 1), tasks);
    }
}
