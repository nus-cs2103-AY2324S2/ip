public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.type = TaskType.DEADLINE;
    }

    @Override
    public String toFileFormat() {
        return super.toFileFormat() + " | " + by;
    }

    @Override
    public String toString() {
        return type.getSymbol() + super.toString() + " (by: " + by + ")";
    }
}