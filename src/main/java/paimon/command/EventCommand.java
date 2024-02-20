package paimon.command;

import java.time.LocalDateTime;

import paimon.ChatException;
import paimon.task.EventTask;
import paimon.task.Task;
import paimon.task.TaskList;
import paimon.util.DateParser;
import paimon.util.UiHandler;




/**
 * Represents a command to add an event task to the task list. The event task includes a description,
 * a start date/time, and an end date/time.
 */
public class EventCommand extends Command {
    private final String description;
    private final String startDateString;
    private final String endDateString;

    /**
     * Constructs an EventCommand with the specified task description, start date/time, and end date/time.
     *
     * @param description     The description of the event task.
     * @param startDateString The start date/time of the event as a string.
     * @param endDateString   The end date/time of the event as a string.
     */
    public EventCommand(String description, String startDateString, String endDateString) {
        this.description = description;
        this.startDateString = startDateString;
        this.endDateString = endDateString;
    }

    /**
     * Executes the event command by parsing the start and end date strings, creating a new {@link EventTask},
     * and adding it to the task list. Notifies the user of the added task through the UI handler.
     *
     * @param tasks The task list to which the event task is added.
     * @return A String to be displayed.
     * @throws ChatException If the start or end date strings cannot be parsed into valid dates/times.
     */
    public String execute(TaskList tasks) throws ChatException {
        LocalDateTime startDate = DateParser.parseDate(startDateString);
        LocalDateTime endDate = DateParser.parseDate(endDateString);
        Task eventTask = new EventTask(this.description, startDate, endDate);
        tasks.addTask(eventTask);
        return UiHandler.getAddTaskMessage(eventTask.getTask(), tasks.getSize());
    }

    /**
     * Indicates that executing this command does not signal the application to exit.
     *
     * @return false always, as adding an event task does not terminate the application.
     */
    public boolean isExit() {
        return false;
    }

}
