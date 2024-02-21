package mona;

/**
 * This class represents a todo task, a simple task with a description
 */
public class Todo extends Task {

    /**
     * Constructor for a todo task
     * @param description the description for the todo task
     */
    public Todo(String description) {
        super(description);
    }
    @Override
    public String parseToLogRepresentation() {
        int completionStatus = this.isDone ? 1 : 0;
        return "T|" + completionStatus + "|" + this.description;
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    @Override
    public void updateDetails(String newDetails) {
        this.description = newDetails;
    }
}
