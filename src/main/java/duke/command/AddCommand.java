package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;


/**
 * Represents a command to add a task to the task list.
 * This class extends the Command class and overrides the execute method to perform
 * the addition operation.
 */
public class AddCommand extends Command {
    protected Task task;

    /**
     * Constructs new AddCommand object with a specified task to be added at execution.
     *
     * @param task the task to be added when executed
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds specified task to the task list and saves to hard disk.
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
        super.tasks.addTask(this.task);
        super.storage.appendTaskToFile(this.task);
        String message = "I've appended this to yer list:\n" + this.task.toString();
        return super.ui.printMessage(message);
    }
}
