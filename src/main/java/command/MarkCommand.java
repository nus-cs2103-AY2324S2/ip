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
    public String execute() {
        Task t = taskList.get(taskIndex);
        t.markAsDone();

        String s = "Nice! I've marked this task as done:\n"
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
