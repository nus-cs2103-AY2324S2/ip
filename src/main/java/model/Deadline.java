package model;

public class Deadline extends Task{
    private String by;

    public Deadline(String label, String by) {
        super(label);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
