package kwuntalk.task;


/**
 * Represents a todo task.
 */
public class Todo extends Task {

    /**
     * Constructor for a todo task.
     *
     * @param task Task description.
     */
    public Todo(String task) {
        super(task);
    }


    /**
     * Format task to store in the Tasks File.
     *
     * @return String representation of the formatted task.
     */
    @Override
    public String formatTask() {
        String status = getStatus() ? "1" : "0";
        return String.format("T | %s | %s", status, super.formatTask());
    }


    /**
     * Return the string representation of the task.
     *
     * @return String representation of the task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
