package yarr.task;

/**
 * Represents a task with a description and a completion status.
 * This class provides methods to retrieve information about the task
 * to either be read or save to the hard disk.
 */
public class Todo extends Task {
    /**
     * Constructs a new Todo object with the specified description.
     *
     * @param description a String representing the task description
     */
    public Todo(String description) {
        super(description);

    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getData() {
        return "T | " + super.getData();
    }
}
