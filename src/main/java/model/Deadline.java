package model;

public class Deadline extends Task{
    private String by;

    /**
     *
     * @param label starting time
     * @param by ending time
     */
    public Deadline(String label, String by) {
        super(label);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns a parsable string of the deadline task. Meant to be used for storage purposes.
     *
     * @return a parsable string representation of the task and all its details
     */
    public String parsableString() {
        return "D|" + super.parsableString() + "|" + by;
    }
}
