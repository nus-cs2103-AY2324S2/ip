package thames.command;

import java.io.IOException;

import thames.TaskList;
import thames.Ui;
import thames.Storage;
import thames.ThamesException;
import thames.task.Task;

/**
 * Subclass of command that deals with editing tasks in task list.
 */
public class EditCommand extends Command {
    /** Whether command is to mark or unmark task */
    protected boolean isMark;
    /** Index of task within task list to be marked/unmarked */
    protected int index;

    public EditCommand(Boolean isMark, int index) {
        this.isMark = isMark;
        this.index = index;
    }

    /**
     * Marks or unmarks the task in the task list.
     * Notifies user of completion before saving the new task list.
     *
     * @param tasks Task list to mark/unmark task from.
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

            Task task = tasks.get(index);
            if (isMark) {
                task.mark();
                storage.save(tasks);
                return ui.mark(task);
            } else {
                task.unmark();
                storage.save(tasks);
                return ui.mark(task);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new ThamesException("Task list index is out of bounds!");
        } catch (IOException e) {
            throw new ThamesException("There was an error saving the file. Please try again.");
        }

    }
}
