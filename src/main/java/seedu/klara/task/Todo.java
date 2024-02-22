package seedu.klara.task;

/**
 * Represents the <code>Todo</code> class to store information
 * about the user-created todo
 */
public class Todo extends Task {

    /**
     * Constructor for <code>Todo</code>.
     * @param description Represents description of <code>Todo</code>
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Represents overridden toString method for printing <code>Todo</code> details.
     * @return details of type <code>String</code>
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
