package task;


public class ToDos extends Task {

    /**
     * Constructs a new ToDos task with the specified description.
     *
     * @param description The description of the task.
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * Overrides the toString method to provide a formatted representation of the to-do task.
     *
     * @return The formatted string representation of the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
