package area;

/**
 * Checks for exception for each command.
 */
public class AreaException extends Exception {

    protected String description;

    /**
     * creates a DukeException object to store error
     * @param description
     */
    public AreaException(String description) {
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
