package haro.task;

/**
 * The ToDo class represents a to-do task in the application, extending the Task class.
 * It provides specific functionality for handling to-do tasks.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo instance with the specified task description.
     *
     * @param task Task description
     */
    public ToDo(String task) {
        super(task);
    }

    /**
     * Constructs a ToDo instance with the specified task description and done status.
     *
     * @param task Task description
     * @param isDone True if the task is marked as done, false otherwise
     */
    public ToDo(String task, boolean isDone) {
        super(task, isDone);
    }

    /**
     * Returns a formatted string representation of the to-do task.
     *
     * @return Formatted string representation of the to-do task
     */
    @Override
    public String printTask() {
        return "[T]" + super.printTask();
    }

    /**
     * Returns a string representation of the to-do task for storage purposes.
     * Format: "T | {marked} | {task}"
     *
     * @return String representation of the to-do task for storage
     */
    @Override
    public String toString() {
        int marked = (this.isDone) ? 1 : 0;
        return "T | " + marked + " | " + this.task;
    }
}
