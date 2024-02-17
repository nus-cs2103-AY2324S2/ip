package duke.command;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Task;

/**
 * Represents a command to add a task with a deadline.
 */
public class DeadlineCommand extends AddTaskCommand {
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

    @Override
    protected Task getTaskFromDescription(String description) throws DukeException {
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
