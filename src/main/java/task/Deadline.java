package task;

public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }
    
    public String getBy() {
        return this.by;
    }

    @Override
    public String toStorageString() {
        return "D | " + super.toStorageString() + " | " + this.getBy();
    }
    
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getBy() + ")";
    }
}
