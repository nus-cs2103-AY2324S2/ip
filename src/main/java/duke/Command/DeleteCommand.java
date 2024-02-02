package duke.Command;

import duke.*;
import duke.Tasks.Task;
import duke.Tasks.TaskList;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a DeleteCommand object with the given task index.
     *
     * @param taskIndex The index of the task to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        this.index = taskIndex;
    }


    /**
     * Executes the DeleteCommand, deleting a task from the task list.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage handler.
     * @throws DukeException If an error occurs during command execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index < 1 || index > tasks.size()) {
            throw new DukeException("OOPS!!! duke.Tasks.Task index is out of range.");
        }

        Task deletedTask = tasks.get(index - 1);
        int count = tasks.size() - 1;
        tasks.delete(index - 1);
        Ui.showDeletedMessage(deletedTask, count);
        storage.save(tasks.getAllTasks());

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

