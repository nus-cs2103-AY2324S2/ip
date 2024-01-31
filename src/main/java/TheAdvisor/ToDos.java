package TheAdvisor;

import java.io.Serializable;

/**
 * Represents a todo task with a description.
 * Extends the Task class and implements Serializable for persistence.
 */
public class ToDos extends Task implements Serializable {
    /**
     * Creates a new Todo object with the given name and deadline.
     *
     * @param description The description of the todo object.
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * Returns the type of the task, which is "T ".
     *
     * @return A string that represents the type of the task.
     */
    @Override
    public String getType() {
        return "T ";
    }

    /**
     * Returns the desciption of the task.
     *
     * @return A string that is the description of the task.
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a string representation of the todo task.
     *
     * @return A string representation of the todo task, including type, status, and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
