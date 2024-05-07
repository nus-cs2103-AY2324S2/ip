package command;

import sky.Ui;
import task.Deadline;
import task.TaskList;
/**
 * Represents a command to add a deadline task.
 */
public class AddDeadlineCommand extends Command {
    private String description;
    private String by;

    /**
     * Creates a new AddDeadlineCommand.
     *
     * @param description The description of the deadline task.
     * @param by The deadline of the task.
     */
    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the command to add a new deadline task.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface.
     * @return The response to the user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        Deadline deadline = new Deadline(description, by);
        tasks.add(deadline);
        return ui.showAddTask(deadline, tasks.size());
    }
}
