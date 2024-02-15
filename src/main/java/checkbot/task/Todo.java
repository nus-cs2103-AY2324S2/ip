package checkbot.task;

/**
 * Represents a todo task in the task list.
 * A todo task is a task without any date/time attached to it.
 */
public class Todo extends Task {
    /**
     * Constructor for Todo.
     *
     * @param name The name of the Todo.
     */
    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    @Override
    public String formatForFile() {
        return "T | " + super.formatForFile();
    }
}
