package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to mark or unmark a specific tasks in the task list.
 * This class extends the Command class and overrides the execute method to
 * mark or unmark a specific task.
 */
public class ToggleMarkCommand extends Command {
    protected int index;
    protected boolean isDone;

    /**
     * Constructs a command that controls if a task at the specified index
     * should be marked or unmarked.
     *
     * @param index an int representing the 1-indexed location of the task
     * @param isDone a boolean representing if a task is to be marked as completed or incomplete
     */
    public ToggleMarkCommand(int index, boolean isDone) {
        this.index = index;
        this.isDone = isDone;
    }

    /**
     * Executes the mark/unmark action on the task depending on the isDone variable.
     *
     * @param tasks The TaskList object on which the command will operate
     * @param storage The Storage object that will read and write to files
     * @param ui The Ui object that handles displaying messages
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        super.tasks = tasks;
        super.storage = storage;
        super.ui = ui;
        String message;
        if (isDone) {
            super.tasks.markTask(index);
            message = "X marks the spot. I've crossed this task of yer list, me heartie!\n"
                    + super.tasks.printTask(index);
        } else {
            super.tasks.unmarkTask(index);
            message = "The winds be shiftin', and I be lettin' "
                    + "this task sail with the breeze unmarked.\n"
                    + super.tasks.printTask(index);
        }
        super.storage.saveTaskListToFile(super.tasks.getTasks());
        return super.ui.printMessage(message);
    }
}
