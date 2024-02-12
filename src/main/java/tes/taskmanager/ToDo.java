package tes.taskmanager;


/**
 * Represents a task without a deadline or designated period.
 */
public class ToDo extends Task {

    /**
     * Constructs a task without a deadline or designated period.
     *
     * @param description Description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "T" + super.toString();
    }
}
