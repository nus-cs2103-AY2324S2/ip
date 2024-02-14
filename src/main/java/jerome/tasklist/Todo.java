package jerome.tasklist;

/**
 * Represents a to-do task with a description and completion status.
 */
public class Todo extends Task {

    /**
     * Constructs a To-do object with the given description and completion status.
     *
     * @param description the description of the to-do task.
     * @param isDone      the completion status of the to-do task.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Constructs a To-do object with the given description, completion status and priority.
     *
     * @param description the description of the to-do task.
     * @param isDone      the completion status of the to-do task.
     * @param priority    the priority level of the task.
     */
    public Todo(String description, boolean isDone, Priority priority) {
        super(description, isDone);
        this.setPriority(priority);
    }


    /**
     * Returns a string representation of the To-do task.
     *
     * @return a string representation of the To-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Represents a to-do task with a description and completion status to be stored in txt file.
     */
    @Override
    public String toStorageString() {
        return "T | " + this.getDescription() + " | " + super.getStatus() + " | " + this.getPriority();
    }


}
