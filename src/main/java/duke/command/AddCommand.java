/*
 * AddCommand.java
 * This class represents a command to add tasks to the Duke application.
 * It handles the addition of Todo, Deadline, and Event tasks based on user input.
 */

package duke.command;

import java.io.IOException;
import java.time.LocalDateTime;

import duke.DukeException;
import duke.Ui;
import duke.task.DateTimeUtil;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Storage;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Represents a command to add tasks to the Duke application.
 */
public class AddCommand extends Command {
    private final String fullCommand;

    public AddCommand(String fullCommand) {
        assert fullCommand != null && !fullCommand.isEmpty() : "Full command cannot be null or empty";
        this.fullCommand = fullCommand;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";

        String[] commandParts = fullCommand.split(" ", 2);
        String taskType = commandParts[0];
        String response;

        if (!taskType.equals("todo") && !taskType.equals("deadline") && !taskType.equals("event")) {
            throw new DukeException("Please enter a valid task type.");
        }

        if (commandParts.length < 2 || commandParts[1].trim().isEmpty()) {
            throw new DukeException("The description of a " + taskType + " cannot be empty.");
        }

        String description = commandParts[1].trim();
        switch (taskType) {
        case "todo":
            tasks.add(new Todo(description));
            response = ui.showAddedTask(tasks);
            break;
        case "deadline":
            String[] deadlineParts = description.split(" /by ");
            if (deadlineParts.length < 2 || deadlineParts[1].trim().isEmpty()) {
                throw new DukeException("The deadline time or task name is missing.");
            }
            tasks.add(new Deadline(deadlineParts[0].trim(), DateTimeUtil.parseDateTime(deadlineParts[1].trim())));
            response = ui.showAddedTask(tasks);
            break;
        case "event":
            String[] eventParts = description.split(" /from ", 2);
            if (eventParts.length < 2 || eventParts[1].trim().isEmpty()) {
                throw new DukeException("The event time details or event times are missing.");
            }

            String eventDescription = eventParts[0].trim();
            if (eventDescription.isEmpty()) {
                throw new DukeException("The event description is missing.");
            }

            String[] timeParts = eventParts[1].split(" /to ", 2);
            if (timeParts.length < 2) {
                throw new DukeException("Incomplete event time details. Both start and end times are required.");
            }

            String startTimeString = timeParts[0].trim();
            String endTimeString = timeParts[1].trim();
            if (startTimeString.isEmpty()) {
                throw new DukeException("The event start time is missing.");
            }
            if (endTimeString.isEmpty()) {
                throw new DukeException("The event end time is missing.");
            }

            LocalDateTime startTime = DateTimeUtil.parseDateTime(startTimeString);
            LocalDateTime endTime = DateTimeUtil.parseDateTime(endTimeString);
            tasks.add(new Event(eventDescription, startTime, endTime));
            response = ui.showAddedTask(tasks);
            break;
        default:
            throw new DukeException("Please enter a valid task type.");
        }
        storage.saveTasks(tasks);
        assert response != null && !response.isEmpty() : "Response cannot be null or empty";
        return response;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
