public class Deadline extends Task {
    protected String by;

    public Deadline(String n, String by) {
        super(n);
        this.by = by;
    }

    public Deadline(String n, boolean d, String by) {
        super(n, d);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }
}
