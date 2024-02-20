package duke.tasks;

import java.time.format.DateTimeFormatter;

/**
 * Represents a todo task.
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo object with the given description.
     *
     * @param description The description of the todo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a new ToDo object with the given description and status.
     *
     * @param description The description of the todo task.
     * @param status      The completion status of the todo task.
     */
    public ToDo(String description, Boolean status) {
        super(description, status);
    }


    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String convertToFileFormat(DateTimeFormatter storeFormatter) {
        return "T | " + super.convertToFileFormat(storeFormatter);
    }
}
