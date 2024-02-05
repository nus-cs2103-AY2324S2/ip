package Thames.command;

import java.io.IOException;
import Thames.TaskList;
import Thames.Ui;
import Thames.Storage;
import Thames.ThamesException;
import Thames.task.Task;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ThamesException {
        try {
            Task task = tasks.get(index);
            if (isMark) {
                task.mark();
                ui.mark(task);
            } else {
                task.unmark();
                ui.mark(task);
            }
            storage.save(tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new ThamesException("Task list index is out of bounds!");
        } catch (IOException e) {
            throw new ThamesException("There was an error saving the file. Please try again.");
        }

    }
}
