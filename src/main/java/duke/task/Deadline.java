package duke.task;

import java.time.LocalDateTime;

/**
 * Task with a deadline attached.
 *
 * @author KohGuanZeh
 */
public class Deadline extends Task {
    // Format to create Deadline task in program.
    public static final String INPUT_DEADLINE_FORMAT = "deadline <task-name> /by <dd-MM-yyyy HH:mm>";

    // Deadline of task.
    private LocalDateTime by;

    /**
     * Creates a Task with given description and specified deadline.
     *
     * @param description Description of task.
     * @param by Deadline for the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the task, task type, completion status and task deadline.
     * Tasks of Event type are marked with [D].
     * Tasks that are done are marked with "[X]" whereas tasks that are not done are marked with "[ ]".
     *
     * @return Task type, completion status, description and deadline.
     */
    @Override
    public String getTaskInformation() {
        return "[D]" + super.getTaskInformation() + "\n    (By " + this.by.format(Task.OUTPUT_DATETIME_FORMAT) + ")";
    }

    /**
     * Returns String to be saved in data file and loaded for future use.
     *
     * @return String data of task.
     */
    @Override
    public String toDataString() {
        StringBuilder sb = new StringBuilder();
        sb.append("D | ").append(this.getIsDoneDataString()).append(" | ").append(this.getDescription())
                .append(" /by ").append(this.by.format(Task.INPUT_DATETIME_FORMAT))
                .append(" | ").append(this.getPriorityDataString());;
        return sb.toString();
    }
}
