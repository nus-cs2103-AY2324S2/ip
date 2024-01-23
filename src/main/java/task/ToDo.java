package task;

/**
 * ToDos: tasks without any date/time attached to it.
 *
 * @author Titus Chew
 */
public class ToDo extends Task {
    /**
     * Constructor for this to-do
     *
     * @param name the name of this to-do
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Gets a human-readable description of this task.
     *
     * @return this task in a human-readable string
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
