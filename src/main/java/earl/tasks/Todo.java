package earl.tasks;

/**
 * Class representing a task of type todo.
 */
public class Todo extends Task {

    /** Class constructor. */
    public Todo(String description) {
        super(description);
        taskType = TaskType.TODO;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toStorageString() {
        return String.format("%s,%s,%s",
                taskType.toString(),
                super.getStatusIcon(),
                getDescription());
    }
}
