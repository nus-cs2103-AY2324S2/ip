package shon.command;

import java.time.format.DateTimeParseException;

import shon.TaskList;
import shon.Ui;

/**
 * Represents a command to add a <code>Deadline</code> task.
 */
public class AddDeadlineCommand extends AddTaskCommand {
    private String by;

    /**
     * Creates a new command to add a <code>Deadline</code> task.
     *
     * @param description The description of the <code>Deadline</code> task to be added.
     * @param by The by datetime of the <code>Deadline</code> task as a String.
     */
    public AddDeadlineCommand(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Adds the <code>Deadline</code> task to the list tasks, and outputs the result of the command.
     *
     * @param tasks The <code>TaskList</code> to add the <code>Deadline</code> task to.
     * @param ui The <code>Ui</code> used to output the result of the command.
     * @throws DateTimeParseException If the given by datetime does not adhere to the expected parse format.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws DateTimeParseException {
        ui.print(tasks.addDeadline(this.description, this.by));
    }
}
