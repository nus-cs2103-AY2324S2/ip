package yue.Command;

import yue.Storage;
import yue.YueException;
import yue.Tasks.Task;
import yue.Tasks.TaskList;

/**
 * Represents a command to mark a task as not done.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs an UnmarkCommand object with the given task index.
     *
     * @param TASK_INDEX The index of the task to mark as not done.
     */
    public UnmarkCommand(int TASK_INDEX) {
        this.index = TASK_INDEX;
    }


    /**
     * Executes the UnmarkCommand, marking the specified task as not done.
     *
     * @param tasks   The list of tasks.
     * @param storage The storage handler.
     * @throws YueException If an error occurs during command execution.
     */

    @Override
    public String execute(TaskList tasks, Storage storage) throws YueException {
        assert tasks != null : "TaskList cannot be null";
        assert storage != null : "Storage cannot be null";

        if (index < 1 || index > tasks.size()) {
            throw new YueException("OOPS!!! duke.Tasks.Task index is out of range.");
        }

        Task task = tasks.get(index - 1);
        task.markNotDone();
        String unmarkedMessage = "    OK, I've marked this task as not done yet:\n" + "      " + task + "\n";

        storage.save(tasks.getAllTasks());

        return unmarkedMessage;
    }



    /**
     * Checks if the command is an exit command.
     *
     * @return Always returns false, as this is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

