package homie;

/**
 * To-do class that extends the Task class. Handles all to-do related tasks.
 */
public class Todo extends Task {

    /**
     * Constructor for to-do object
     *
     * @param description The String description of the to-do task
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " + super.toString();
    }
}
