package shon.command.task;

import java.time.format.DateTimeParseException;

import shon.task.TaskList;


/**
 * Represents a command to add a <code>Deadline</code> task.
 */
public class AddDeadlineCommand extends AddTaskCommand {
    private String by;

    /**
     * Creates a new command to add a <code>Deadline</code> task.
     *
     * @param tasks The TaskList associated with this command.
     * @param description The description of the <code>Deadline</code> task to be added.
     * @param by The by datetime of the <code>Deadline</code> task as a String.
     */
    public AddDeadlineCommand(TaskList tasks, String description, String by) {
        super(tasks, description);
        this.by = by;
    }

    /**
     * Adds the <code>Deadline</code> task to the list tasks, and outputs the result of the command.
     *
     * @throws DateTimeParseException If the given by datetime does not adhere to the expected parse format.
     */
    @Override
    public String execute() throws DateTimeParseException {
        return String.join("\n", this.tasks.addDeadline(this.description, this.by));
    }
}
