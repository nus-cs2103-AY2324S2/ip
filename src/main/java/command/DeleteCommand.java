package command;

import task.Task;
import task.TaskList;
import dook.Storage;
import dook.DookException;

public class DeleteCommand extends Command {
    private final int positionToDelete;

    public DeleteCommand(int positionToDelete) {
        this.positionToDelete = positionToDelete;
    }

    /**
     * Deletes a command from the TaskList.
     *
     * @param tasks The bot TaskList.
     * @param storage The storage interface.
     * @throws DookException If TaskList is empty or indexed out of bounds.
     */
    public String execute(TaskList tasks, Storage storage) throws DookException {
        String toReturn;
        Task toDelete;
        try {
            toDelete = tasks.get(positionToDelete - 1);
        } catch (IndexOutOfBoundsException e) {
            DookException err;
            if (tasks.size() == 0) {
                err = new DookException("Nooo! You don't have any tasks to delete :(");
            } else {
                err = new DookException(String.format("Nooo! "
                                + "You have %d tasks!"
                                + " Valid inputs for delete is in the range [1 - %d]",
                        tasks.size(), tasks.size()));
            }
            throw err;
        }
        toReturn = "Oki! Bye Bye task!\n";
        tasks.remove(positionToDelete - 1);
        toReturn += "You deleted this task :(\n";
        toReturn += toDelete.toString() + "\n";
        storage.write(tasks);
        return toReturn;
    }
}
