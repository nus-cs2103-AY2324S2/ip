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
        if (commandParts.length < 2 || commandParts[1].trim().isEmpty()) {
            throw new DukeException("The description cannot be empty.");
        }

        String taskType = commandParts[0];
        String description = commandParts[1].trim();

        switch (taskType) {
        case "todo":
            return addTodoTask(tasks, ui, storage, description);
        case "deadline":
            return addDeadlineTask(tasks, ui, storage, description);
        case "event":
            return addEventTask(tasks, ui, storage, description);
        default:
            throw new DukeException("Please enter a valid task type.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private String addTodoTask(TaskList tasks, Ui ui, Storage storage, String description) throws IOException {
        tasks.add(new Todo(description));
        String response = ui.showAddedTask(tasks);
        storage.saveTasks(tasks);
        return response;
    }

    private String addDeadlineTask(TaskList tasks, Ui ui, Storage storage, String description) throws DukeException, IOException {
        String[] deadlineParts = description.split(" /by ");
        if (deadlineParts.length < 2 || deadlineParts[1].trim().isEmpty()) {
            throw new DukeException("The deadline time or task name is missing.");
        }
        LocalDateTime deadlineTime = DateTimeUtil.parseDateTime(deadlineParts[1].trim());
        tasks.add(new Deadline(deadlineParts[0].trim(), deadlineTime));
        String response = ui.showAddedTask(tasks);
        storage.saveTasks(tasks);
        return response;
    }

    private String addEventTask(TaskList tasks, Ui ui, Storage storage, String description) throws DukeException, IOException {
        String[] eventParts = description.split(" /from ", 2);
        if (eventParts.length < 2 || eventParts[1].trim().isEmpty()) {
            throw new DukeException("The event time details or event times are missing.");
        }
        String eventDescription = eventParts[0].trim();
        String[] timeParts = eventParts[1].split(" /to ", 2);
        if (timeParts.length < 2) {
            throw new DukeException("Incomplete event time details. Both start and end times are required.");
        }
        LocalDateTime startTime = DateTimeUtil.parseDateTime(timeParts[0].trim());
        LocalDateTime endTime = DateTimeUtil.parseDateTime(timeParts[1].trim());
        tasks.add(new Event(eventDescription, startTime, endTime));
        String response = ui.showAddedTask(tasks);
        storage.saveTasks(tasks);
        return response;
    }
}