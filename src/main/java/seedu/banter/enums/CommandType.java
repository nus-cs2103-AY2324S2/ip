package seedu.banter.enums;

/**
 * Represents the different types of commands that can be executed.
 */
public enum CommandType {
    LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND;

    /**
     * Returns the string representation of the command type in lowercase.
     * @return String representation of the command type in lowercase.
     */
    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
