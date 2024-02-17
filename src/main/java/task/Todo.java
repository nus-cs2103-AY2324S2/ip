package task;

/**
 * Todos are tasks without any date/time attached to it.
 */
public class Todo extends Task {
    protected String name;

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
     * @param name The task name.
     * @param isDone Whether the task is completed.
     */
    public Todo(String name, boolean isDone) {
        super(isDone);
        this.name = name;
    }

    @Override
    public boolean isMatchKeyword(String keyword) {
        return this.name.toLowerCase().contains(keyword.toLowerCase());
    }

    @Override
    public String toString() {
        return super.toString() + "[T] " + this.name;
    }
}
