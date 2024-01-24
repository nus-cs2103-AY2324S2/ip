public class Deadline extends Task {
    String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        String info = String.format("(by: %s)", by);
        return String.format("[D]%s%s %s", this.getStatusIcon(), super.toString(), info);
    }
}
