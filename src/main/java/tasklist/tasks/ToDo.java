package tasklist.tasks;

/** Represents a Todo Task that needs to be completed. It is an extension of the Task class. */
public class ToDo extends Task {
    /**
     * intialize the ToDo task.
     *
     * @param item the task to be completed.
     */
    public ToDo(String item) {
        super(item);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
