package missa.command;

import missa.TaskList;
import missa.Ui;

/**
 * Represents unmark commands.
 */
public class UnmarkCommand extends Command {
    private int index;
    private TaskList tasks;

    /**
     * Create an unmark command.
     *
     * @param tasks Task list that stores tasks.
     * @param index Index of task to be marked as not done.
     */
    public UnmarkCommand(TaskList tasks, int index) {
        this.tasks = tasks;
        this.index = index;
    }

    @Override
    public TaskList execute(Ui ui) {
        tasks.unmarkTask(index);
        System.out.println(ui.replyUnmarkCommand());
        return tasks;
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
