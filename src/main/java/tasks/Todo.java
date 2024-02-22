package tasks;

/**
 * For the creation of a Todo class
 */
public class Todo extends Task {

    /**
     * Constructor for the Todo class
     * @param name Name of the task
     */
    public Todo(String name) {
        super(name);
    }

    @Override
    public String taskTypeDisplay() {
        return "[T]";
    }
    @Override
    public String storeFormat() {
        String completeFormat = isComplete ? "1" : "0";
        return String.format("%s | %s | %s", "T", completeFormat, name);
    }
}
