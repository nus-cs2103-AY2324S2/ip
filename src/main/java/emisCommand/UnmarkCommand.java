package emisCommand;

import emis.TaskList;
import emis.Storage;
import emisExceptions.EmisException;

/**
 * The UnmarkCommand class represents a command to mark a task as undone in the EMIS application.
 * When executed, it marks the specified task as undone and updates the storage.
 */
public class UnmarkCommand extends Command {
    /** The index of the task to mark as undone. */
    private int taskNo;

    /**
     * Constructs a new UnmarkCommand object with the specified task index.
     * 
     * @param taskNo The index of the task to mark as undone.
     */
    public UnmarkCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    /**
     * Executes the unmark command by marking the specified task as undone and updating the storage.
     * 
     * @param tasklist The TaskList object representing the list of tasks.
     * @param ui The Ui object handling interactions with the user.
     * @param storage The Storage object handling loading and saving of tasks.
     */
    @Override
    public String execute(TaskList tasklist, Storage storage) {
        try {
            String response = tasklist.markAsUndone(this.taskNo);
            storage.updateStorage();
            return response;
        } catch (EmisException e) {
            return e.getMessage();
        }
    }
}