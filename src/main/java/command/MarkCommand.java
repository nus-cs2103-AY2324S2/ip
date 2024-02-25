package command;

import roland.Storage;
import roland.TaskList;
import roland.Ui;
import task.Task;


/**
 * The MarkCommand class represents a command to mark a task as done or undone in the TaskList based on its index.
 * It extends the Command class and implements the execute method to perform the marking operation.
 * Upon execution, it marks the specified task as done or undone, updates the user interface, and provides feedback
 * about the status change.
 *
 * @author wolffe88
 */

public class MarkCommand extends Command {

    private final int index;
    private final boolean isMarkDone;

    /**
     * Constructs a MarkCommand with the specified index and markDone flag.
     *
     * @param index    The index of the task to be marked.
     * @param markDone A boolean flag indicating whether to mark the task as done (true) or undone (false).
     */
    public MarkCommand(int index, boolean markDone) {
        this.index = index;
        this.isMarkDone = markDone;
    }

    /**
     * Marks the task in the TaskList as done or undone based on the specified index and markDone flag,
     * updates the user interface, and provides feedback about the status change.
     *
     * @param tasks   The TaskList that stores the tasks.
     * @param ui      The user interface that outputs to the terminal.
     * @param storage The storage path to store persistent data.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.get(this.index - 1);
        if (isMarkDone) {
            task.markDone();
            return (task.toString());
        } else {
            task.markUndone();
            return (task.toString());
        }
    }
}
