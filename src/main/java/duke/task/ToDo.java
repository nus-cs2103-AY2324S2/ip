package duke.task;

import duke.utility.DukeException;
import duke.utility.Parser;

/**
 * Class that represents a ToDo Task.
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo Object.
     *
     * @param description String containing the description of the Task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the ToDo Object as a String.
     *
     * @return String Representation of the ToDo Object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Updates one of the value/description of the fields in this task.
     *
     * @param field field to be updated.
     * @param updatedDescription description to be updated to.
     * @throws DukeException
     */
    @Override
    public void updateTaskDescription(String field, String updatedDescription) throws DukeException {
        String fieldInput = field.toLowerCase();
        switch (fieldInput) {
        case "info":
            this.description = updatedDescription;
            break;
        default:
            throw new DukeException("*Honk!* Pengu can only update the 'info' of this task");
        }
    }
}
