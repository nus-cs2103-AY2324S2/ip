package junjie.commands;

import junjie.TaskList;
import junjie.Ui;
import junjie.tasks.Task;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    /**
     * The command word to delete a task.
     */
    public static final String COMMAND_WORD = "delete";
    private static final String MESSAGE = "ok i help you delete this task ah:\n%s";
    private static final String ERROR_MESSAGE = "oi the argument must be a number la";

    private final String index;

    /**
     * Constructs a command to delete the task at the given index.
     *
     * @param index The index of the task to delete.
     */
    public DeleteCommand(String index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui) {
        try {
            int index = Integer.parseInt(this.index);

            Task task = tasks.get(index - 1);
            tasks.remove(index - 1);
            return String.format(MESSAGE, task);
        } catch (NumberFormatException e) {
            return ERROR_MESSAGE;
        }
    }
}
