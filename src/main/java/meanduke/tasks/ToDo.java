package meanduke.tasks;

/**
 * This class represents a basic Task without any time constraints.
 */
public class ToDo extends Task {

    private static final String TYPE_SYMBOL = "[T]";

    /**
     * Constructs a basic ToDo task.
     *
     * @param description Description of the ToDo Task
     */
    public ToDo(String description) {
        super(description, TYPE_SYMBOL);
    }

    /**
     * Constructs a basic ToDo task.
     *
     * @param description Description of the ToDo Task
     * @param isDone      boolean value that determines if the initialised ToDo Task is completed or not
     */
    public ToDo(String description, Boolean isDone) {
        super(description, TYPE_SYMBOL, isDone);
    }

    @Override
    public String saveString() {
        return "TODO" + "\n" + super.saveString();
    }
}
