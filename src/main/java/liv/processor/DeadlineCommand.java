package liv.processor;

import liv.container.Storage;
import liv.task.Deadline;
import liv.container.TaskList;
import liv.ui.Ui;

/**
 * Represents a command that adds a deadline to the task list.
 */
public class DeadlineCommand extends Command {
    private Deadline deadline;

    /**
     * Constructor for the class
     * @param deadline The deadline object created.
     */
    public DeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }

    /**
     * Adds the deadline to the list and signals the Ui to respond.
     *
     * @param tasks   The list of tasks to operate on.
     * @param ui      The Ui to gives interaction with users.
     * @param storage The storage where the data is stored.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(deadline);
        String message = Ui.getDeadlineMessage(deadline);
        storage.saveTaskToFile();
        return message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasChangedData() {
        return true;
    }
}
