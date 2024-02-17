package nicky.command;

import java.io.IOException;
import java.time.LocalDateTime;

import nicky.NickyException;
import nicky.Ui;
import nicky.task.DateTimeUtil;
import nicky.task.Deadline;
import nicky.task.Event;
import nicky.task.Storage;
import nicky.task.TaskList;
import nicky.task.Todo;

/**
 * Represents a command to add tasks to the Nicky application.
 * It handles the addition of Todo, Deadline, and Event tasks based on user input.
 */
public class AddCommand extends Command {
    private final String fullCommand;

    /**
     * Constructs a new AddCommand object with the provided full command string.
     * @param fullCommand the full command string representing the add operation
     */
    public AddCommand(String fullCommand) {
        assert fullCommand != null && !fullCommand.isEmpty() : "Full command cannot be null or empty";
        this.fullCommand = fullCommand;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws NickyException, IOException {
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";

        String[] commandParts = fullCommand.split(" ", 2);
        if (commandParts.length < 2 || commandParts[1].trim().isEmpty()) {
            throw new NickyException("The description cannot be empty.");
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
            throw new NickyException("Please enter a valid task type.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Adds a Todo task to the task list and saves the tasks to the file.
     *
     * @param tasks the list of tasks
     * @param ui the user interface
     * @param storage the storage for saving tasks to the file
     * @param description the description of the Todo task
     * @return the response message after adding the task
     * @throws IOException if there is an issue saving the tasks to the file
     */
    private String addTodoTask(TaskList tasks, Ui ui, Storage storage,
                               String description) throws IOException {
        tasks.add(new Todo(description));
        String response = ui.showAddedTask(tasks);
        storage.saveTasks(tasks);
        return response;
    }

    /**
     * Adds a Deadline task to the task list and saves the tasks to the file.
     *
     * @param tasks the list of tasks
     * @param ui the user interface
     * @param storage the storage for saving tasks to the file
     * @param description the description of the Deadline task
     * @return the response message after adding the task
     * @throws NickyException if there is an issue adding the task
     * @throws IOException if there is an issue saving the tasks to the file
     */
    private String addDeadlineTask(TaskList tasks, Ui ui, Storage storage,
                                   String description) throws NickyException, IOException {
        String[] deadlineParts = description.split(" /by ");
        if (deadlineParts.length < 2 || deadlineParts[1].trim().isEmpty()) {
            throw new NickyException("The deadline time or task name is missing.");
        }
        LocalDateTime deadlineTime = DateTimeUtil.parseDateTime(deadlineParts[1].trim());
        tasks.add(new Deadline(deadlineParts[0].trim(), deadlineTime));
        String response = ui.showAddedTask(tasks);
        storage.saveTasks(tasks);
        return response;
    }

    /**
     * Adds an Event task to the task list and saves the tasks to the file.
     *
     * @param tasks the list of tasks
     * @param ui the user interface
     * @param storage the storage for saving tasks to the file
     * @param description the description of the Event task
     * @return the response message after adding the task
     * @throws NickyException if there is an issue adding the task
     * @throws IOException if there is an issue saving the tasks to the file
     */
    private String addEventTask(TaskList tasks, Ui ui, Storage storage,
                                String description) throws NickyException, IOException {
        String[] eventParts = description.split(" /from ", 2);
        if (eventParts.length < 2 || eventParts[1].trim().isEmpty()) {
            throw new NickyException("The event time details or event times are missing.");
        }
        String eventDescription = eventParts[0].trim();
        String[] timeParts = eventParts[1].split(" /to ", 2);
        if (timeParts.length < 2) {
            throw new NickyException("Incomplete event time details. Both start and end times are required.");
        }
        LocalDateTime startTime = DateTimeUtil.parseDateTime(timeParts[0].trim());
        LocalDateTime endTime = DateTimeUtil.parseDateTime(timeParts[1].trim());
        tasks.add(new Event(eventDescription, startTime, endTime));
        String response = ui.showAddedTask(tasks);
        storage.saveTasks(tasks);
        return response;
    }
}
