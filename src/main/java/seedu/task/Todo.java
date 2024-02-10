package seedu.task;

/**
 * Todos are tasks without any date/time attached to it.
 */
public class Todo extends Task {
    protected String name = "";

    /**
     * Constructor for a todo object.
     *
     * @param name seedu.task name
     */
    public Todo(String name) {
        super();
        this.name = name;
    }

    /**
     * Constructor for a loading from file.
     *
     * @param name seedu.task name
     * @param b isDone
     */
    public Todo(String name, boolean b) {
        super(b);
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString() + "[T] " + this.name;
    }
}
