package task;

/**
 * The Todo class represents a simple task without a specific date.
 * It extends the Task class and provides a formatted string representation.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the specified name.
     * @param name The name of the todo task.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Provides a formatted string representation of the Todo task.
     * @return A string representing the Todo task in a readable format.
     */
    @Override
    public String toString() {
        return "[T]" + (super.isMarked ? "[X] " : "[ ] ") + super.name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Todo) {
            Todo todo = (Todo) obj;
            return this.name.equals(todo.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        String hashString = String.format("todo,%s", name);
        return hashString.hashCode();
    }
}
