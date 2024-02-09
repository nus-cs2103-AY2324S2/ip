package duke.task;

/**
 * Task with no time limit attached.
 *
 * @author KohGuanZeh
 */
public class ToDo extends Task {
    // Format to create todo task in program.
    public static final String INPUT_TODO_FORMAT = "todo <task-name>";

    /**
     * Creates a new task with no time limit attached.
     *
     * @param description Description of task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the task, task type and its completion status.
     * Tasks of ToDo type are marked with [T].
     * Tasks that are done are marked with "[X]" whereas tasks that are not done are marked with "[ ]".
     *
     * @return Task type, completion status and description.
     */
    @Override
    public String getTaskInformation() {
        return "[T]" + super.getTaskInformation();
    }

    /**
     * Returns String to be saved in data file and loaded for future use.
     *
     * @return String data of task.
     */
    @Override
    public String toDataString() {
        StringBuilder sb = new StringBuilder();
        sb.append("T | ").append(this.getIsDoneDataString()).append(" | ").append(this.getDescription())
                .append(" | ").append(this.getPriorityDataString());
        return sb.toString();
    }
}
