package lemona.task;

/**
 * Represents a todo task in the task manager application.
 * A todo task has a description and can be marked as done or undone.
 */
public class Todo extends Task{

    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String print() {
        String str = "[T]" + super.print();
        return str;
    }

    @Override
    public String getDescription() {
        String str = "[T] " + super.getDescription();
        return str;
    }

    @Override
    public String getTaskInfo() {
        return "[T] " + "/ [" + super.getStatusIcon()
                + "] / " + super.getTaskInfo();
    }
}
