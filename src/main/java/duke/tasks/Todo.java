package duke.tasks;

public class Todo extends Task {
    /**
     * {@inheritDoc}
     *
     * This subclass <code>Todo</code> behaves the same as <code>Task</code>, but with
     * a different <code>toString()</code>.
     *
     * @param n Specified todo name.
     */
    public Todo(String n) {
        super(n);
    }

    /**
     * Constructs a <code>Todo</code> specifying a name and if it is complete.
     *
     * @param n Specified todo name.
     * @param d If the task is done.
     */
    public Todo(String n, boolean d) {
        super(n, d);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
