package command;

import task.Task;
import task.TaskList;

/**
 * {@inheritDocs}
 * Marks a task as done.
 */
public class MarkCommand extends Command {
    private TaskList taskList;
    private int taskIndex;

    /**
     * Creates an instance of MarkCommand.
     */
    public MarkCommand(TaskList taskList, int taskIndex) {
        this.taskList = taskList;
        this.taskIndex = taskIndex;
    }

    /**
     * {@inheritDocs}
     * Marks a task as done.
     */
    @Override
    public void execute() {
        Task t = taskList.get(taskIndex);
        t.markAsDone();

        System.out.println("Nice! I've marked this task as done:");
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
