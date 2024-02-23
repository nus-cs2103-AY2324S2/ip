package junjie.commands;

import junjie.TaskList;
import junjie.Ui;
import junjie.tasks.Task;

/**
 * A command to mark a task as done.
 */
public class MarkCommand extends Command {
    /**
     * The command word to mark a task as done.
     */
    public static final String COMMAND_WORD = "mark";
    private static final String MESSAGE = "good job bro, marked this task as done:\n%s";
    private static final String ERROR_MESSAGE = "oi the argument must be a number la";

    private final String index;

    /**
     * Constructs a command to mark the task at the given index.
     *
     * @param index The index of the task to mark.
     */
    public MarkCommand(String index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui) {
        try {
            int index = Integer.parseInt(this.index);
            Task task = tasks.get(index - 1);
            task.markDone(true);
            return String.format(MESSAGE, tasks.get(index - 1));
        } catch (NumberFormatException e) {
            return ERROR_MESSAGE;
        }
    }
}
