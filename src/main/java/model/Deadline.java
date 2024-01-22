package model;

public class Deadline extends Task {
    private final String time;
    public Deadline(String title, String time) {
        super(title);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.time + ")";
    }
}
