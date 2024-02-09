package duke;

public class Todo extends Task {
    /**
     * Constructor for Todo class.
     *
     * @param description The description of the task.
     * @param done        The status of the task.
     */
    public Todo(String description, boolean done) {
        super(description, done);
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
