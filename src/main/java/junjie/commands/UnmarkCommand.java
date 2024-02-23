package junjie.commands;

import junjie.TaskList;
import junjie.Ui;
import junjie.tasks.Task;

/**
 * Represents a command to unmark a task as done.
 */
public class UnmarkCommand extends Command {
    /**
     * The command word to unmark a task.
     */
    public static final String COMMAND_WORD = "unmark";
    private static final String MESSAGE = "ok i help you unmark this task ah:\n%s";
    private static final String ERROR_MESSAGE = "oi the argument must be a number la";

    private final String index;

    /**
     * Constructs a command to unmark the task at the given index.
     *
     * @param index The index of the task to unmark.
     */
    public UnmarkCommand(String index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui) {
        try {
            int index = Integer.parseInt(this.index);
            Task task = tasks.get(index - 1);
            task.markDone(false);
            return String.format(MESSAGE, tasks.get(index));
        } catch (NumberFormatException e) {
            return ERROR_MESSAGE;
        }
    }
}
