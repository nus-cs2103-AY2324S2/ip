package task;
/** This class represnts a task to be done */
public class Todo extends Task{
    /**
     * Constructs a Todo object based on the description of the task
     * @param description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string value of the task with the [T] tag
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
