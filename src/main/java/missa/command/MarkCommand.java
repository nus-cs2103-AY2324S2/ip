package missa.command;

import missa.TaskList;
import missa.Ui;

/**
 * Represents mark commands.
 */
public class MarkCommand extends Command {
    private int index;
    private TaskList tasks;

    /**
     * Create a mark command.
     *
     * @param tasks Task list that stores all tasks.
     * @param index Index of the task to be marked as done.
     */
    public MarkCommand(TaskList tasks, int index) {
        this.tasks = tasks;
        this.index = index;
    }

    @Override
    public TaskList execute(Ui ui) {
        tasks.markTask(index);
        System.out.println(ui.replyMarkCommand());
        return tasks;
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
