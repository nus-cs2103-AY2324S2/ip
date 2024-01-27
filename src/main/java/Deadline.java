public class Deadline extends Task {
    public static final String TYPE_SYMBOL = "D";
    private final String due;

    public Deadline(String description, String due) {
        super(description);
        this.due = due;
    }

    public Deadline(String description, boolean isDone, String due) {
        super(description, isDone);
        this.due = due;
    }

    @Override
    public String toCsv() {
        return TYPE_SYMBOL + "," + (super.getDone() ? "1" : "0") + "," + super.getDescription() + "," + this.due + ",";
    }

    @Override
    public String toString() {
        return "[" + TYPE_SYMBOL + "]" + super.toString() + " (by: " + this.due + ")";
    }
}
