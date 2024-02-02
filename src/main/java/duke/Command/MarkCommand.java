package duke.Command;


import duke.Tasks.Task;
import duke.Tasks.TaskList;
import duke.Ui;
import duke.Storage;
import duke.DukeException;


/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructs a MarkCommand object with the given task index.
     *
     * @param taskIndex The index of the task to mark as done.
     */
    public MarkCommand(int taskIndex) {
        this.index = taskIndex;
    }



    /**
     * Executes the MarkCommand, marking the specified task as done.
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

        Task task = tasks.get(index - 1);
        task.markDone();
        ui.showMarkedMessage(task);
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
