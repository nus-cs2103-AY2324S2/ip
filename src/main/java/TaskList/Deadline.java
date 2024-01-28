package TaskList;

public class Deadline extends Task {

    private String by;

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toStorageString() {
        return "D | " + this.getDescription() + " | " + super.getStatus() + " | " + this.by;
    }



}
