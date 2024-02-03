public class Deadline extends Task {
    private String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String parsedFormatToSave() {
        return String.format("D | %c | %s | %s",
                this.isDone ? 'y' : 'n', this.description, this.by);
    }
}
