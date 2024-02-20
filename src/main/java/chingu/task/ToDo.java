package chingu.task;

/**
 * This class is implementation of Task which contains description.
 */
public class ToDo extends Task {

    /**
     * Creates an instance of ToDos which contains description detail of the task.
     * @param description
     */
    public ToDo(String description, String priority) {
        super(description, priority);
    }

    /**
     * Returns String of task detail with type of Task (Events) and description of the task.
     * @return
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
