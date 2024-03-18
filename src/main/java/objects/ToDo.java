package objects;

import java.io.Serializable;

/**
 * ToDos is a class representing a todo task.
 * It extends Task and implements the Serializable interface for object serialization.
 */
public class ToDo extends Task implements Serializable {

    /**
     * Constructs a new todo task with the given name.
     *
     * @param name The name of the todo task.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Returns a string representation of the todo task.
     *
     * @return The formatted string representation of the todo task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
