package area;

/**
 * Checks for exception for each command.
 */
public class DukeException extends Exception {

    protected String description;

    /**
     * creates a DukeException object to store error
     * @param description
     */
    public DukeException(String description) {
        this.description = description;
    }

    /**
     * returns description of error
     * @return description
     */
    @Override
    public String toString() {
        return this.description;
    }
}
