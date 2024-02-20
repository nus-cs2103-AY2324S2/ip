package javassist.task;

/**
 * Represents a todo.
 */
public class Todo extends Task {
    public Todo(String task) {
        super(task);
    }

    /**
     * {@inheritDoc}
     *
     * @return String with type of task and description of todo.
     */
    @Override
    public String printTask() {
        return "[T]" + super.printTask();
    }

    @Override
    public String toString() {
        return String.format("T | %s", super.toString());
    }
}
