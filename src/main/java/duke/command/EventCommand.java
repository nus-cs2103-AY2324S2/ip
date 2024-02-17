package duke.command;

import duke.DukeException;
import duke.task.Event;
import duke.task.Task;

/**
 * Represents a command to add a task with a starting time and an ending time.
 */
public class EventCommand extends AddTaskCommand {
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
    protected Task getTaskFromDescription(String description) throws DukeException {
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
