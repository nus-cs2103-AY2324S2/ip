package duke.task;


public class ToDo extends Task {

    /**
     * Constructor for ToDo task.
     *
     * @param description Description of ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructor for ToDo task with status. Used for file access.
     *
     * @param status isDone status of task. Either "1" or "0".
     * @param description Description of ToDo task.
     */
    public ToDo(String status, String description) {
        super(status.equals("1"), description);
    }

    @Override
    public String toFile() {
        return "T | " + (isDone ? "1" : "0") + " | " + description + " | " + tagName;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
