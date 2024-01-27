import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Task with a from and to date and time.
 *
 * @author KohGuanZeh
 */
public class Event extends Task {
    // Format to create event task in program.
    public static String CREATE_EVENT_FORMAT = "event <task-name> /from <dd-MM-yyyy HH:mm> /to <dd-MM-yyyy HH:mm>";

    // Start datetime.
    private LocalDateTime from;

    // End datetime.
    private LocalDateTime to;

    /**
     * Creates a task with given description and specified duration.
     * Duration includes both date and time.
     *
     * @param description Description of task.
     * @param from Start datetime of event.
     * @param to End datetime of event.
     */
    private Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Creates a new Event task based on user input.
     *
     * @param input Input String after the "event" command.
     * @return New Event task.
     * @throws TaskException Exception when task cannot be created.
     */
    public static Event createEvent(String input) throws TaskException {
        input = input.trim();
        try {
            String[] tokens = input.split(" /from ");
            String description = tokens[0];
            if (description.isEmpty()) {
                throw new TaskException("Error. Unable to create task.\nFormat: " + Event.CREATE_EVENT_FORMAT);
            }
            tokens = tokens[1].split(" /to ");
            LocalDateTime from = LocalDateTime.parse(tokens[0], Task.INPUT_DATETIME_FORMAT);
            LocalDateTime to = LocalDateTime.parse(tokens[1], Task.INPUT_DATETIME_FORMAT);
            return new Event(description, from, to);
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw new TaskException("Error. Unable to create task.\nFormat: " + Event.CREATE_EVENT_FORMAT);
        }
    }

    /**
     * Returns the task, task type, completion status and event duration.
     * Tasks of Event type are marked with [E].
     * Tasks that are done are marked with "[X]" whereas tasks that are not done are marked with "[ ]".
     *
     * @return Task type, completion status, description and duration.
     */
    @Override
    public String getTaskInformation() {
        return "[E]" + super.getTaskInformation() + " (from: " + this.from.format(Task.OUTPUT_DATETIME_FORMAT)
                + " to: " + this.to.format(Task.OUTPUT_DATETIME_FORMAT) + ")";
    }

    /**
     * Returns String to be saved in data file and loaded for future use.
     *
     * @return String data of task.
     */
    @Override
    public String saveTaskAsString() {
        return "E | " + (this.getIsDone() ? 1 : 0) + " | " + this.getDescription()
                + " /from " + this.from.format(Task.INPUT_DATETIME_FORMAT)
                + " /to " + this.to.format(Task.INPUT_DATETIME_FORMAT);
    }
}
