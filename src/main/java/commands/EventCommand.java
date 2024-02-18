package commands;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import exception.EventFormatException;
import storage.Storage;
import task.Event;
import task.Task;
import task.TaskList;

/**
 * Represents the command used to add an event to the task list.
 */
public class EventCommand extends Command {

    public static final String COMMAND_WORD = "event";
    private static final String SUCCESS_MESSAGE = "Got it. Uncle added this event:\n\t %s"
            + "\n Now you have %s task(s) in the list.";
    private final String message;

    /**
     * Creates a new EventCommand object with the provided message.
     *
     * @param message Input message containing description as well as the start and end date.
     */
    public EventCommand(String message) {
        this.message = message;
    }

    /**
     * Executes the EventCommand, adding an event task to the task list based on the provided input message.
     * The input message is expected to contain a description and the start and end date,
     * separated by "/from" and "/to".
     * The description represents the task's details, and the start date is the date the task begins,
     * and the end date is the date the task ends.
     * If the input does not follow the correct format
     * or if the start and/or end dates are invalid, an EventFormatException is thrown.
     *
     * @param tasks   The TaskList representing the collection of tasks.
     * @param storage The Storage object handling storage operations.
     * @throws EventFormatException Thrown when the input does not follow the correct deadline command format
     *                                or if the provided deadline start and/or end date is invalid.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws EventFormatException {
        String[] args = message.split("/from");
        if (args.length == 1 || args.length > 2) {
            throw new EventFormatException();
        }
        String desc = args[0].trim();
        String[] duration = args[1].split("/to");
        if (duration.length == 1 || duration.length > 2) {
            throw new EventFormatException();
        }
        String start = duration[0].trim();
        String end = duration[1].trim();

        try {
            Task event = new Event(desc, LocalDate.parse(start), LocalDate.parse(end));
            tasks.addTasks(event);
            storage.appendToFile(tasks);
            return String.format(SUCCESS_MESSAGE, event, tasks.numTasks());
        } catch (IOException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            throw new EventFormatException();
        }
    }
}

