package chatteroo.tasks;

/**
 * Represents a todo task with a description and a status of whether it is done.
 */
public class ToDo extends Task {
    /**
     * Constructor for the ToDo class.
     * @param description The description of the task.
     */
    public ToDo (String description) {
        super(description, "T");
    }

    //Overridden toString method to print type of task and description
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
