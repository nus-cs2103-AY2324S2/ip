public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String isDone, String description, String by) {
        super(isDone, description);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        String info = String.format("(by: %s)", by);
        return String.format("[D]%s%s %s", this.getStatusIcon(), super.toString(), info);
    }
}
