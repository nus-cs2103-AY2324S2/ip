package command;

import roland.Storage;
import task.Task;
import roland.TaskList;
import roland.Ui;

public class MarkCommand extends Command {

    private final int index;
    private final boolean markDone;

    /**
     * Constructs a MarkCommand with the specified index and markDone flag.
     *
     * @param index    The index of the task to be marked.
     * @param markDone A boolean flag indicating whether to mark the task as done (true) or undone (false).
     */
    public MarkCommand(int index, boolean markDone) {
        this.index = index;
        this.markDone = markDone;
    }

    /**
     * Marks the task in the TaskList as done or undone based on the specified index and markDone flag,
     * updates the user interface, and provides feedback about the status change.
     *
     * @param tasks   The TaskList that stores the tasks.
     * @param ui      The user interface that outputs to the terminal.
     * @param storage The storage path to store persistent data.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.get(this.index-1);
        if (markDone) {
            task.markDone();
            System.out.println(ui.getBot() + task.toString());
        } else {
            task.markUndone();
            System.out.println(ui.getBot() + task.toString());
        }
    }
}
