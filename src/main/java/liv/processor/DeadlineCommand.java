package liv.processor;

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
     * @param tasks The list of tasks to operate on.
     * @param ui The Ui to gives interaction with users.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.addTask(deadline);
        Ui.displayDeadlineCommand(deadline);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean changedData() {
        return true;
    }
}
