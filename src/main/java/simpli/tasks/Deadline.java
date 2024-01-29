package simpli.tasks;

public class Deadline extends Task {
    protected String by;

    public Deadline(boolean isDone, String name, String by) {
        super(isDone, name);
        this.by = by;
    }

    public String toCsv() {
        return String.format("Deadline,%s,%s", super.toCsv(), this.by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }
}
