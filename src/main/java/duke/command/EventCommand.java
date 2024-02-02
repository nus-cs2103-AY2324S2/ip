package duke.command;

import duke.DukeException;
import duke.state.ProgramState;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command to add a task with a starting time and an ending time.
 */
public class EventCommand extends Command {
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

    /**
     * Executes the command. This command adds a task with a starting time and an
     * ending time to the task list. The program state is set to normal after the
     * command is executed, even if the command fails to execute.
     *
     * @param list  The task list to be modified.
     * @param state The program state to be modified.
     * @return The response to be displayed to the user.
     * @throws DukeException If the user input is invalid (empty task
     *                       description/starting time/ending time), or if the
     *                       command fails to execute.
     */
    public String execute(TaskList list, ProgramState state) throws DukeException {
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
        Task task = new Event(taskDescription, startTime, endTime);
        list.addTask(task);
        String response = ("Added: " + task + "\nYou now have " + list.size() + " tasks in the list.");
        state.setNormal();
        return response;
    }
}
