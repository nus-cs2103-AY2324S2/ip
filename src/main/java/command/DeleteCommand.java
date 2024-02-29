package command;

import common.DukeException;
import task.Task;
import task.TaskList;

/**
 * {@inheritDocs}
 * Deletes a task from a tasklist.
 */
public class DeleteCommand extends Command {
    private TaskList taskList;
    private int taskIndex;

    /**
     * Creates an instance of DeleteCommand.
     */
    public DeleteCommand(TaskList taskList, int taskIndex) {
        this.taskList = taskList;
        this.taskIndex = taskIndex;
    }

    /**
     * {@inheritDocs}
     * Deletes a task from a tasklist.
     *
     * @throws DukeException If the command cannot be executed.
     */
    @Override
    public void execute() {
        Task t = taskList.remove(taskIndex);

        System.out.println("Noted, I've removed this task:");
        System.out.println(" " + t.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
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
