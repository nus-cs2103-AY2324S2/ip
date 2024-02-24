package homie;

/**
 * To-do class that extends the Task class.
 * Throws a To-do exception when description is empty
 */
public class Todo extends Task {
    private String DIVIDER = "________________________________________";

    /**
     * Constructor for to-do object
     * @param description The String description of the to-do task
     * @throws TodoException The exception when to-do description is empty
     */
    public Todo(String description) throws TodoException {
        super(description);
        if (description.isEmpty()) {
            throw new TodoException("\n" + DIVIDER + "\nOpps!!! The description of a todo cannot be empty.\n"
                    + DIVIDER);
        }

    }

    @Override
    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " + super.toString();
    }
}
