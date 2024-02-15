package duke.exception;


/**
 * Represents an exception when there are duplicate tasks in the database.
 */
public class DuplicateTaskException extends DukeException {

    /**
     * Return the string representation of the exception.
     *
     * @return String representation of the exception.
     */
    @Override
    public String toString() {
        return String.format("%s This task has already exists ;(\n",
                super.toString());
    }
}
