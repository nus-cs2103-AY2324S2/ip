/**
 * Todos are tasks without any date/time attached to it.
 */
public class Todo extends Task {
    /**
     * Constructor for a todo object.
     *
     * @param name task name
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * toString method for printing task description.
     * @return task type + task status + task name
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
