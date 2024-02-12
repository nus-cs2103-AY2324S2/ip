package duke.task;

import java.util.Objects;

/**
 * The `Todo` class represents a basic task without a specific deadline or duration in Duke.
 */
public class Todo extends Task {
    /**
     * Constructs a `Todo` with the given description.
     *
     * @param description The description of the todo.
     */
    public Todo(String description) {
        super(description);
    }
    /**
     * Returns a string representation of the todo task, including its status icon.
     *
     * @return String representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    /**
     * Returns a formatted string for writing the todo task to a file.
     *
     * @return Formatted string for file representation.
     */
    @Override
    public String toFileString() {
        return "T" + super.toFileString();
    }
    /**
     * Indicates whether some other object is "equal to" this one. Equality is based on task properties.
     *
     * @param obj The reference object with which to compare.
     * @return True if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Todo todo = (Todo) obj;
        return this.isDone == todo.isDone
                && this.description.equals(todo.description);
    }
    /**
     * Returns a hash code value for the object.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.description, this.isDone);
    }
}
