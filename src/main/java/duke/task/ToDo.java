package duke.task;

import duke.command.CommandType;

/**
 * duke.task.ToDo task class.
 */
public class ToDo extends Task {

    /**
     * Constructor of todo class.
     *
     * @param description to describe the todo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructor of todo class.
     *
     * @param description to describe the todo task.
     * @param isDone task isDone or not done.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns String representation of duke.task.ToDo task.
     *
     * @return String representation of duke.task.ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns String representation for storage.
     *
     * @return String representation for storage of duke.task.ToDo task.
     */
    @Override
    public String toStorageString() {
        return CommandType.TODO.toString() + " , " + super.toStorageString();
    }

}
