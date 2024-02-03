package duke;
/**
 * The Todo class represents a task.
 * It has description about the task and it is a subclass of the Task class.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo class.
     *
     * @param description The description of the task.
     */
    public Todo (String description) {
        super(description);
    }

    /**
     * Returns a string format of the Event object.
     *
     * @return A string representation of the Event object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
