/**
 * Task with a from and to date and time.
 *
 * @author KohGuanZeh
 */
public class Event extends Task {
    // Format to create event task in program.
    public static String CREATE_EVENT_FORMAT = "event <task-name> /from <from> /to <to>";

    // Start datetime.
    private String from;

    // End datetime.
    private String to;

    /**
     * Creates a task with given description and specified duration.
     * Duration includes both date and time.
     *
     * @param description Description of task.
     * @param from Start datetime of event.
     * @param to End datetime of event.
     */
    private Event(String description, String from, String to) {
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
            tokens = tokens[1].split(" /to ");
            String from = tokens[0], to = tokens[1];
            if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                throw new TaskException("Error. Unable to create task.\nFormat: " + Event.CREATE_EVENT_FORMAT);
            }
            return new Event(description, from, to);
        } catch (IndexOutOfBoundsException e) {
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
        return "[E]" + super.getTaskInformation() + " (from: " + this.from + " to: " + this.to + ")";
    }

    /**
     * Returns String to be saved in data file and loaded for future use.
     *
     * @return String data of task.
     */
    @Override
    public String saveTaskAsString() {
        return "E | " + (this.getIsDone() ? 1 : 0) + " | " + this.getDescription()
                + " /from " + this.from + " /to " + this.to;
    }
}
