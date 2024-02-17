package fluffy.command;

import java.time.LocalDate;

import fluffy.storage.Storage;
import fluffy.task.Deadline;
import fluffy.tasklist.TaskList;
import fluffy.ui.Ui;

/**
 * Represents a command to add a deadline task.
 */
public class DeadlineCommand extends Command {

    protected String description;
    protected LocalDate by;

    /**
     * Constructor for DeadlineCommand.
     * @param description The description of the deadline.
     * @param by The date of the deadline.
     */
    public DeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the command to add a deadline task.
     * @param tasks The list of tasks.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to save the tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline deadline = new Deadline(description, by);
        tasks.addTask(deadline);
        ui.showTaskAdded(deadline, tasks.getSize());
    }

    /**
     * Returns whether the command is an exit command.
     * @return Whether the command is an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
