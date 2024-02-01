package Command;

import Task.Task;
import Task.TaskList;
import Dook.Ui;
import Dook.Storage;
import Dook.DookException;

public class DeleteCommand extends Command {
    private final int positionToDelete;

    public DeleteCommand(int positionToDelete) {
        this.positionToDelete = positionToDelete;
    }

    /**
     * Deletes a command from the TaskList.
     *
     * @param tasks The bot TaskList.
     * @param ui The user interface.
     * @param storage The storage interface.
     * @throws DookException If TaskList is empty or indexed out of bounds.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DookException {
        Task toDelete;
        try {
            toDelete = tasks.get(positionToDelete - 1);
        } catch (IndexOutOfBoundsException e) {
            DookException err;
            if (tasks.size() == 0) {
                err = new DookException("Nooo! You don't have any tasks to delete :(");
            } else {
                err = new DookException(String.format("Nooo! " +
                                "You have %d tasks!" +
                                " Valid inputs for delete is in the range [1 - %d]",
                        tasks.size(), tasks.size()));
            }
            throw err;
        }
        ui.println("Oki! Bye Bye task!");
        tasks.remove(positionToDelete - 1);
        ui.println("You deleted this task :(");
        ui.println(toDelete.toString());
        storage.write(tasks);
    }
}
