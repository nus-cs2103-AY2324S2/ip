package homie;

/**
 * The Todo class used to create a Todo Object.
 * A subclass of Task class.
 * Has a description.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo object.
     *
     * @param description The String description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " + super.toString();
    }
}
