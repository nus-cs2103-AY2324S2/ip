package jojo;

/**
 * Represents a todo with a string description.
 */
public class ToDo extends Task {
    public ToDo (String description) {
        super(description);
    }

    /**
     * Returns a simplified toString for the ease of saving.
     * @return String
     */
    @Override
    public String simpleToString() {
        return "T " + super.simpleToString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
