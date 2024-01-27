package MissMinutes;

import java.io.Serializable;

/**
 * Represents a specialised task with no additional information.
 */
public class Todo extends Task implements Serializable {
    /**
     * Creates a `Todo` object with the given name
     * @param name The name of the Todo object
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Creates a new Todo object from the user input (string)
     *
     * @param input The string representation of the Todo object
     * @return The Todo object deserialized from the string
     * @throws MissMinutesException If the given string is in incorrect format
     */
    public static Todo fromStr(String input) throws MissMinutesException {
        if (input.isEmpty()) {
            throw new MissMinutesException("Todos have to be created with the following format: todo <desc>");
        } else {
            return new Todo(input);
        }
    }

    /**
     * The string representation of the Todo object
     *
     * @return The string representation
     */
    @Override
    public String toString() {
        return  "[T]" + super.toString();
    }
}
