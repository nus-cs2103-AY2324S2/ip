package duke.Command;

import duke.Storage;
import duke.DukeException;
import duke.Tasks.Task;
import duke.Tasks.TaskList;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private int INDEX;

    /**
     * Constructs a MarkCommand object with the given task index.
     *
     * @param TASK_INDEX The index of the task to mark as done.
     */
    public MarkCommand(int TASK_INDEX) {
        this.INDEX = TASK_INDEX;
    }



    /**
     * Executes the MarkCommand, marking the specified task as done.
     *
     * @param tasks   The list of tasks.
     * @param storage The storage handler.
     * @throws DukeException If an error occurs during command execution.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        assert tasks != null : "TaskList cannot be null";
        assert storage != null : "Storage cannot be null";


        if (INDEX < 1 || INDEX > tasks.size()) {
            throw new DukeException("OOPS!!! duke.Tasks.Task index is out of range.");
        }

        Task task = tasks.get(INDEX - 1);
        task.markDone();

        String markedMessage = "    Nice! I've marked this task as done:\n" + "      " + task + "\n";

        storage.save(tasks.getAllTasks());

        return markedMessage;
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
