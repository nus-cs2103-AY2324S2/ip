public class Deadline extends Task {

    private String by;

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }

    @Override
    public String formatData() {
        return "D" + " | " + this.formatTask() + " | " + this.by;
    }
}
