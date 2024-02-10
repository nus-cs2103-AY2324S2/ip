package duke;

/**
 * Constructs a todo task.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo with a description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the description of the saved task.
     *
     * @return The description of the saved task.
     */
    @Override
    public String saveData() {
        return "T | " + (isDone() ? "1" : "0") + " | " + getDescription();
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns whether the task is equal to another object.
     *
     * @param obj The object to be compared.
     * @return Whether the task is equal to the object.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Todo todo = (Todo) obj;
        return this.getDescription().equals(todo.getDescription());
    }
}
