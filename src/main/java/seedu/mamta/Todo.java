package seedu.mamta;

/**
 * Represents a todo task.
 * Inherits properties and methods from the Task class.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo object with the given content.
     * @param content The content of the todo task.
     */
    Todo(String content) {
        super(content);
    }

    /**
     * Constructs a Todo object with the given completion status and content.
     * @param isComplete The completion status of the todo task.
     * @param content The content of the todo task.
     */
    public Todo(boolean isComplete, String content) {
        super(isComplete, content);
    }

    /**
     * Marks the todo task as done.
     * @return A new Todo object with the todo task marked as done.
     */
    @Override
    public Todo markDone() {
        return new Todo(true, this.content);
    }

    /**
     * Marks the todo task as not done.
     * @return A new Todo object with the todo task marked as not done.
     */
    @Override
    public Todo unmarkTask() {
        return new Todo(false, this.content);
    }

    /**
     * Returns a string representation of the Todo object.
     * @return A string representation of the Todo object.
     */
    public String toString() {
        if (this.isComplete) {
            return String.format("T|X|%s", this.content);
        } else {
            return String.format("T| |%s", this.content);
        }
    }
}

