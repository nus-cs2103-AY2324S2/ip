package simpli.tasks;

/**
 * A basic task that is required to be completed.
 */
public class Todo extends Task {
    /**
     * Initalizes a todo task with the specified attributes.
     *
     * @param isDone boolean true if the todo task is completed. False if otherwise.
     * @param name String.
     */
    public Todo(boolean isDone, String name) {
        super(isDone, name);
    }

    /**
     * Returns the todo task as a comma-separated values (csv) String representation.
     *
     * @return String representing the csv value for the todo task.
     */
    @Override
    public String toCsv() {
        return "Todo," + super.toCsv();
    }

    /**
     * Returns the todo task String representation.
     *
     * @return String representation of the todo task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
