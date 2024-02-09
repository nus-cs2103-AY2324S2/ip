package duke.command;

import duke.task.Task;
import duke.TaskList;
import duke.Storage;
import duke.Ui;

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
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        super.tasks = tasks;
        super.storage = storage;
        super.ui = ui;
        super.tasks.addTask(this.task);
        super.storage.appendTaskToFile(this.task);
        String message = "I've appended this to yer list:\n" + this.task.toString();
        super.ui.printMessage(message);
    }
}
