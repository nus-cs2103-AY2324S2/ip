package objects;

/**
 * The Todo class represents the Todo task.
 */
public class Todo extends Task {

    /**
     * Constructor for the ToDo class
     *
     * @param description: Description of the ToDo task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toFileString() {
        String taskType = "T";
        String isDone = this.getStatus() ? "1" : "0";
        String description = super.toFileString();
        return taskType + " | " + isDone + " | " + description;
    }

    @Override
    public String toString() {
        String taskType = "[T]";
        String toDoString = taskType + super.toString();
        return toDoString;
    }
}
