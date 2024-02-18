package sylvia.command;

import sylvia.SylviaException;
import sylvia.task.Deadline;
import sylvia.task.Task;

/**
 * Represents a command to add a task with a deadline.
 */
public class DeadlineCommand extends AddTaskCommand {
    private static final String MANUAL = "Usage: deadline <description> /by <due time>\n\n"
            + "Adds a task with a deadline to the task list.\n"
            + "The due time must be specified in the format: yyyy-mm-dd hh:mm. The description cannot be empty.\n\n"
            + "Example: deadline return book /by 2024-02-18 18:00\n\n" + "Aliases: dl";
    private String taskDescription;
    private String dueTime;

    /**
     * Constructs a new deadline command.
     *
     * @param body The body of the command.
     */
    public DeadlineCommand(String body) {
        super(body);
        String[] parts = body.split("/by", 2);
        this.taskDescription = parts[0].trim();
        this.dueTime = parts.length > 1 ? parts[1].trim() : "";
    }

    /**
     * Gets the manual for the deadline command.
     *
     * @return The manual for the deadline command.
     */
    public static String getManual() {
        return MANUAL;
    }

    @Override
    protected Task getTaskFromDescription(String description) throws SylviaException {
        if (taskDescription.isEmpty()) {
            throw new EmptyTaskDescriptionException("The description of a deadline cannot be empty.",
                    "The description of a deadline cannot be empty.");
        } else if (dueTime.isEmpty()) {
            throw new EmptyDateTimeException("The due time of a deadline cannot be empty.",
                    "The due time of a deadline cannot be empty.");
        }
        return new Deadline(taskDescription, dueTime);
    }
}
