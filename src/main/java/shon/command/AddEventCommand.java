package shon.command;

import java.time.format.DateTimeParseException;

import shon.TaskList;

/**
 * Represents a command to add an <code>Event</code> task.
 */
public class AddEventCommand extends AddTaskCommand {
    private String from;
    private String to;

    /**
     * Creates a new command to add an <code>Event</code> task.
     *
     * @param description The description of the <code>Event</code> task to be added.
     * @param from The from datetime of the <code>Event</code> task as a String.
     * @param to The to datetime of the <code>Event</code> task as a String.
     */
    public AddEventCommand(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Adds the <code>Event</code> task to the list tasks, and outputs the result of the command.
     *
     * @param tasks The <code>TaskList</code> to add the <code>Deadline</code> task to.
     * @throws DateTimeParseException If the given from or to datetime does not adhere to the expected parse format.
     */
    @Override
    public String execute(TaskList tasks) throws DateTimeParseException {
        return String.join("\n", tasks.addEvent(this.description, this.from, this.to));
    }
}
