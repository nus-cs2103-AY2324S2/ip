package duke;
/**
 * Todo class
 */
public class Todo extends Task {
    /**
     * Constructor for Todo
     * 
     * @param description
     */
    public Todo (String description) {
        super(description);
    }
    @Override
    protected String getTaskType() {
        return "T";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}