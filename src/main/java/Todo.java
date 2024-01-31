/**
 * Todos are tasks without any date/time attached to it.
 */
public class Todo extends Task {
    protected String name = "";

    /**
     * Constructor for a todo object.
     *
     * @param name task name
     */
    public Todo(String name) {
        super();
        this.name = name;
    }

    /**
     * Constructor for a loading from file.
     *
     * @param name task name
     * @param b isDone
     */
    public Todo(String name, boolean b) {
        super(b);
        this.name = name;
    }

    /**
     * toString method for printing task description.
     * @return task status + task type + task name
     */
    @Override
    public String toString() {
        return super.toString() + "[T] " + this.name;
    }
}
