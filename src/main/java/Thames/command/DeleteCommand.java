package Thames.command;

import java.io.IOException;

import Thames.TaskList;
import Thames.Ui;
import Thames.Storage;
import Thames.ThamesException;
import Thames.task.Task;

/**
 * Subclass of Command that deals with deleting tasks from task list
 */
public class DeleteCommand extends Command {
    /** index of task to be deleted from task list */
    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes task from given task list.
     * Notifies user of completion before saving new task list.
     *
     * @param tasks Task list to delete task from.
     * @param ui User interface to notify user of completion.
     * @param storage Storage object that saves new task list.
     * @throws ThamesException If error occurs while saving new task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ThamesException {
        try {
            assert storage != null: "Task list cannot be null!";
            assert tasks != null: "Ui cannot be null!";
            assert ui != null: "Storage cannot be null!";

            Task deletedTask = tasks.remove(index);
            storage.save(tasks);
            return ui.delete(deletedTask, tasks.size());
        } catch (IOException e) {
            throw new ThamesException("There was an error saving the file. Please try again.");
        } catch (IndexOutOfBoundsException e) {
            throw new ThamesException("Task list index is out of bounds!");
        }
    }
}
