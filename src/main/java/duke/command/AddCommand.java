/*
 * AddCommand.java
 * This class represents a command to add tasks to the Duke application.
 * It handles the addition of Todo, Deadline, and Event tasks based on user input.
 */

package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Storage;
import duke.task.TaskList;
import duke.task.Todo;
import duke.task.DateTimeUtil;

import java.io.IOException;
import java.time.LocalDateTime;

public class AddCommand extends Command {
    private final String fullCommand;

    public AddCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        String[] commandParts = fullCommand.split(" ", 2);
        String taskType = commandParts[0];

        if(!taskType.equals("todo") && !taskType.equals("deadline") && !taskType.equals("event")) {
            throw new DukeException("Please enter a valid task type.");
        }

        if (commandParts.length < 2 || commandParts[1].trim().isEmpty()) {
            throw new DukeException("The description of a " + taskType + " cannot be empty.");
        }

        String description = commandParts[1].trim();
        switch (taskType) {
            case "todo":
                tasks.add(new Todo(description));
                break;
            case "deadline":
                String[] deadlineParts = description.split(" /by ");
                if (deadlineParts.length < 2 || deadlineParts[1].trim().isEmpty()) {
                    throw new DukeException("The deadline time or task name is missing.");
                }
                tasks.add(new Deadline(deadlineParts[0].trim(), DateTimeUtil.parseDateTime(deadlineParts[1].trim())));
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
                break;
            default:
                throw new DukeException("Please enter a valid task type.");
        }
        ui.showAddedTask(tasks);
        storage.saveTasks(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
