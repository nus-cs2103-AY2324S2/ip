package Mitsuki;

/**
 * Contains information for the creation of Todo objects.
 *
 * @author Tee Chu Jie
 */
public class Todo extends Task {
    /**
     * The constructor for a Todo object.
     *
     * @param description The description of the todo object to be created.
     *                    Handled by the super constructor in the Task class.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Converts the Todo object into a human-readable String to be displayed to the user.
     * @return String object that represents the Todo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
