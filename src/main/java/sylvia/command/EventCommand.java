package sylvia.command;

import sylvia.SylviaException;
import sylvia.task.Event;
import sylvia.task.Task;

/**
 * Represents a command to add a task with a starting time and an ending time.
 */
public class EventCommand extends AddTaskCommand {
    public static final String MANUAL = "Usage: event <description> /from <start time> /to <end time>\n"
            + "Adds a task with a starting time and an ending time to the task list.\n"
            + "The start time and end time must be specified in the format: yyyy-mm-dd hh:mm\n"
            + "Example: event project meeting /from 2024-02-18 18:00 /to 2024-02-18 20:00\n" + "Aliases: ev";
    private String taskDescription;
    private String startTime;
    private String endTime;

    /**
     * Constructs a new event command.
     *
     * @param body The body of the command.
     */
    public EventCommand(String body) {
        super(body);
        String[] parts = body.split("/from|/to", 3);
        this.taskDescription = parts[0].trim();
        this.startTime = parts.length > 1 ? parts[1].trim() : "";
        this.endTime = parts.length > 2 ? parts[2].trim() : "";
    }

    @Override
    protected Task getTaskFromDescription(String description) throws SylviaException {
        if (taskDescription.isEmpty()) {
            throw new EmptyTaskDescriptionException("The description of an event cannot be empty.",
                    "The description of an event cannot be empty.");
        } else if (startTime.isEmpty()) {
            throw new EmptyDateTimeException("The start time of an event cannot be empty.",
                    "The start time of an event cannot be empty.");
        } else if (endTime.isEmpty()) {
            throw new EmptyDateTimeException("The end time of an event cannot be empty.",
                    "The end time of an event cannot be empty.");
        }
        return new Event(taskDescription, startTime, endTime);
    }
}
