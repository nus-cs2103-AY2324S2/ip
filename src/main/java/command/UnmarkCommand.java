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
    public String execute() {
        Task t = taskList.get(taskIndex);
        t.unmark();

        String s = "OK, I've marked this task as not done yet:\n"
                + " " + t.toString();

        System.out.println(s);

        return s;
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
