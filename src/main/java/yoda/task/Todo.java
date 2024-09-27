package yoda.task;


/**
 * Represents a "Todo" task. It is a simple task with only a description.
 */
public class Todo extends Task {

    /**
     * Constructs a todo task with the given description.
     *
     * @param description A string describing the todo task.
     */
    public Todo(String description) {
        super(description); // Call to the superclass (Task) constructor
    }

    /**
     * Returns the type of the task.
     *
     * @return A string representing the type of the task.
     */
    @Override
    public String getType() {
        return "T";
    }

    /**
     * Sets the new description of the task.
     * @param newDescription The new description for the task.
     */
    @Override
    public void setDescription(String newDescription) {
        super.setDescription(newDescription);
    }

    /**
     * Returns a string representation of the event, including its type,
     * @return
     */
    @Override
    public String toFileFormatDetails() {
        return getDescription();
    }

    /**
     * Returns a string representation of the todo task.
     * The method overrides the Task class's toString method to add the specific
     * identifier for Todo tasks.
     *
     * @return A formatted string representing the todo task,
     *         prefixed with "[T]" to denote a Todo type task.
     */
    @Override
    public String toString() {
        return "[" + getType() + "]" + super.toString(); // Adding the Todo identifier to the string representation
    }
}
