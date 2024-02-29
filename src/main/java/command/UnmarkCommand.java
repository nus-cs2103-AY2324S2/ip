package command;

import task.Task;
import task.TaskList;

/**
 * {@inheritDocs}
 * Marks a task as undone.
 */
public class UnmarkCommand extends Command {
    private TaskList taskList;
    private int taskIndex;

    /**
     * Creates an instance of UnmarkCommand.
     */
    public UnmarkCommand(TaskList taskList, int taskIndex) {
        this.taskList = taskList;
        this.taskIndex = taskIndex;
    }

    /**
     * {@inheritDocs}
     * Marks a task as undone.
     */
    @Override
    public void execute() {
        Task t = taskList.get(taskIndex);
        t.unmark();

        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(" " + t.toString());
    }

    /**
     * {@inheritDoc}
     *
     * @return True if program will exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
