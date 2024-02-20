package yue.command;

import yue.Storage;
import yue.YueException;
import yue.tasks.Task;
import yue.tasks.TaskList;


/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a DeleteCommand object with the given task index.
     *
     * @param TASK_INDEX The index of the task to be deleted.
     */
    public DeleteCommand(int TASK_INDEX) {
        assert TASK_INDEX > 0 : "Task index must be greater than 0";
        this.index = TASK_INDEX;
    }


    /**
     * Executes the DeleteCommand, deleting a task from the task list.
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
            throw new YueException("OOPS!!! The index is out of range.");
        }

        Task deletedTask = tasks.get(index - 1);
        int count = tasks.size() - 1;
        tasks.delete(index - 1);

        String deletedMessage = "    Noted. I've removed this task:\n" + "      " + deletedTask + "\n" +
                "    Now you have " + count + " tasks in the list.\n";


        storage.save(tasks.getAllTasks());
        return deletedMessage;

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

