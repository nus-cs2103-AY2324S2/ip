package paimon.task;

/**
 * Represents a to-do task in the application. This type of task does not have a deadline or time frame
 * and is simply marked as done or not done. It extends the {@link Task} class to inherit its basic task
 * functionalities.
 */
public class TodoTask extends Task {

    /**
     * Constructs a new {@code TodoTask} with the specified description.
     *
     * @param description The textual description of the to-do task.
     */
    public TodoTask(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the to-do task, including its status (done or not done)
     * and description. The task type is indicated by "[T]".
     *
     * @return A string representing the to-do task's status and description.
     */
    @Override
    public String getTask() {
        return (isDone ? "[T][X] " + this.description : "[T][ ] " + this.description);
    }

    /**
     * Returns the string representation of the to-do task for saving to a file. This includes
     * the task type ("T"), its status (done or not done), and description.
     *
     * @return A string suitable for file storage that captures the to-do task's details.
     */
    @Override
    public String toFileString() {
        if (this.isDone) {
            return "T | 1 | " + this.description;
        } else {
            return "T | 0 | " + this.description;
        }
    }
}
