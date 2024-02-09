package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

/**
 * DeleteCommand class represents a command to delete a task from the task list.
 * It extends the Command class and provides methods to execute the command.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a DeleteCommand object with the specified index of the task to delete.
     *
     * @param index The index of the task to delete.
     */
    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    /**
     * Executes the delete command by removing the task at the specified index from the task list.
     *
     * @param taskList The list of tasks.
     * @param ui       The user interface.
     * @param storage  The storage handler.
     * @throws DukeException If an error occurs during command execution.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (taskList.size() == 0) {
            throw new DukeException("OOPS!!! There are no tasks to delete.");
        }
        Task removed = taskList.remove(index);
        System.out.println("Noted. I've removed this task");
        System.out.println("  " + removed);
        System.out.printf("Now you have %d tasks in the list.%n", taskList.size());
    }
}
