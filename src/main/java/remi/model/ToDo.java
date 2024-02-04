package remi.model;

public class ToDo extends Task{
    public ToDo(String label) {
        super(label);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a parsable string of the todo task. Meant to be used for remi.storage purposes.
     *
     * @return a parsable string representation of the task and all its details
     */
    @Override
    public String getParsableString() {
        return String.format("T|%s", super.getParsableString());
    }
}
