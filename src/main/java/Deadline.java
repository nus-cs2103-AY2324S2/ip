public class Deadline extends Task {

    private String by;
    public Deadline(String name, String by) {
        this(name, by, false);
    }

    public Deadline(String name, String by, boolean mark) {
        super(name, mark);
        this.by = by;
    }

    @Override
    public String toSaveString() {
        return String.format("D\t%s\t%s", super.toSaveString(), by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }
}
