package commands;

import Exceptions.InvalidInputException;
import TaskList.Tasks.Task;
import TaskList.Tasks.Event;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Represents a command to add an event to the task list.
 * A <code>AddEventCommand</code> object corresponds to a command with a description, a start time and an end time
 * e.g., <code>"event project meeting /from 2020-12-12 1800 /to 2020-12-12 2000"</code>
 */
public class AddEventCommand extends Command{
    public static final String COMMAND_WORD = "event";
    private final Task event;

    /**
     * Constructs an AddEventCommand object with a description, a start time and an end time.
     * @param eventName the description of the event
     * @param start the start time of the event
     * @param end the end time of the event
     * @throws InvalidInputException if the input is invalid
     */
    public AddEventCommand(String eventName, LocalDateTime start, LocalDateTime end) throws InvalidInputException {
        this.event = new Event(eventName, start, end);
    }

    /**
     * Executes the command to add an event to the task list.
     * @return a string representing the result of executing the command
     * @throws IOException if an I/O error occurs
     */
    @Override
    public String execute() throws IOException {
        assert this.event != null : "Event object should not be null.";
        this.taskList.addTask(this.event);
        return "I have added this task:\n" + this.event + "\n" +
                "Now you have " + this.taskList.size() + " tasks in your list.";

    }
}
