package kai;

import java.io.Serializable;

/**
 * Represents a simple to-do task without a specific deadline or event time.
 * Inherits from the Task class and implements Serializable for object serialization.
 */

public class Todo extends Task implements Serializable {

    /**
     * Constructs a Todo instance with the provided description.
     *
     * @param description The description of the to-do task.
     */
    public Todo(String description) {
        super(description);

    }

    /**
     * Returns a string representation of the to-do task, including its type indicator.
     *
     * @return A string representing the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
