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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ThamesException {
        try {
            Task deletedTask = tasks.remove(index);
            ui.delete(deletedTask, tasks.size());
            storage.save(tasks);
        } catch (IOException e) {
            throw new ThamesException("There was an error saving the file. Please try again.");
        } catch (IndexOutOfBoundsException e) {
            throw new ThamesException("Task list index is out of bounds!");
        }
    }
}
