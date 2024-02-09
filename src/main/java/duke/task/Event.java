package duke.task;

import java.time.LocalDateTime;

/**
 * Task with a from and to date and time.
 *
 * @author KohGuanZeh
 */
public class Event extends Task {
    // Format to create event task in program.
    public static final String INPUT_EVENT_FORMAT = "event <task-name> /from <dd-MM-yyyy HH:mm> /to <dd-MM-yyyy HH:mm>";

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
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
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
        return "[E]" + super.getTaskInformation() + "\n    (" + this.from.format(Task.OUTPUT_DATETIME_FORMAT)
                + " to " + this.to.format(Task.OUTPUT_DATETIME_FORMAT) + ")";
    }

    /**
     * Returns String to be saved in data file and loaded for future use.
     *
     * @return String data of task.
     */
    @Override
    public String toDataString() {
        StringBuilder sb = new StringBuilder();
        sb.append("E | ").append(this.getIsDoneDataString()).append(" | ").append(this.getDescription())
                .append(" /from ").append(this.from.format(Task.INPUT_DATETIME_FORMAT))
                .append(" /to ").append(this.to.format(Task.INPUT_DATETIME_FORMAT))
                .append(" | ").append(this.getPriorityDataString());;
        return sb.toString();
    }
}
