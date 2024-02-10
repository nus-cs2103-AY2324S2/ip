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
     * Constructs a Todo object that allows specifying if task is done or not
     * @param description
     * @param isDone
     */
    public Todo(String description, Boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a string value of the task with the [T] tag
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toDataFormat() {
        String isDone = this.isDone ? "1 | " : "0 | ";
        return "T | " + isDone + this.description;
    }
}
