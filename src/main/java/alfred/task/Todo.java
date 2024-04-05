package alfred.task;

/**
 * Represents a Todo task in a task management application.
 * A Todo task is a basic task type that includes only a task name and completion status,
 * without an associated time or deadline.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with the specified name.
     *
     * @param name The name of the Todo task. This name is passed to the superclass (Task) constructor.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Retrieves the type identifier for a Todo task.
     *
     * @return A string representing the type of the task, specifically "[T]" for Todo tasks.
     */
    @Override
    public String getType() {
        return "[T]";
    }

}