package bytebuddy.tasks;

import java.util.Objects;

/**
 * The Todo class represents a simple task without a specified deadline or duration.
 * It extends the Task class and provides specific implementations for task creation and string representations.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with the given description.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a new Todo task with completion status, description, and optional information.
     *
     * @param completed   The completion status of the task (1 for done, 0 for not done).
     * @param description The description of the task.
     */
    public Todo(String completed, String description) {
        super(description, completed.equals("1"));
    }

    /**
     * Returns a formatted string representation of the Todo task for writing into output file.
     *
     * @return The formatted output string.
     */
    @Override
    public String getTextFormattedOutput() {
        int intIsDone = isDone ? 1 : 0;
        return String.format("T | %d | %s", intIsDone, description);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * This method considers two Todo objects equal if they have the same description and completion status.
     *
     * @param obj the reference object with which to compare.
     * @return true if this Todo is the same as the obj argument; false otherwise.
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
        return isDone == todo.isDone && Objects.equals(description, todo.description);
    }

    /**
     * Returns a hash code value for the Todo object. This method is supported for the benefit of
     * hash tables such as those provided by HashMap.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(description, isDone);
    }

    /**
     * Returns a string representation of the Todo task.
     *
     * @return The string representation.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
