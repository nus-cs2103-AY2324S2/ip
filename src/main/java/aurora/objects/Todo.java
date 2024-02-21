package aurora.objects;

/** The Todo class represents the Todo task. */
public class Todo extends Task {

    private static final String TASK_TYPE_FOR_FILE = "T";
    private static final String TASK_TYPE = "[T]";

    /**
     * Constructs a Todo object.
     *
     * @param description: Description of the Todo object.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toFileString() {
        String isDone = this.getStatus() ? "1" : "0";
        String description = super.toFileString();
        return TASK_TYPE_FOR_FILE + " | " + isDone + " | " + description;
    }

    @Override
    public String toString() {
        String toDoString = TASK_TYPE + super.toString();
        return toDoString;
    }
}
