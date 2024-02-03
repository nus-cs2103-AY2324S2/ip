package remi.model;

public class ToDo extends Task{

    /**
     * Simple constructor for a Todo task.
     *
     * @param label the name of the task
     */
    public ToDo(String label) {
        super(label);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a parsable string of the Todo task. Meant to be used for remi.storage purposes.
     *
     * @return a parsable string representation of the task and all its details
     */
    @Override
    public String parsableString() {
        return String.format("T|%s", super.parsableString());
    }
}
