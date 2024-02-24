package hammy.task;//package duke;

/**
 * The TodoTask class represents a simple to-do task.
 * It is a subclass of the Task class.
 */
public class TodoTask extends Task {

    /**
     * Constructs a TodoTask object with the given description.
     * The task is initialized as not done.
     *
     * @param description the description of the task
     */
    public TodoTask(String description) {
        super(description);
    }

    /**
     * Constructs a TodoTask object with the given description and status.
     *
     * @param description the description of the task
     * @param isDone      the status of the task, true if it is done, false otherwise
     */
    public TodoTask(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a string representation of the TodoTask.
     * The string contains the status (done or not done) and the description of the task.
     *
     * @return a string representation of the TodoTask
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
