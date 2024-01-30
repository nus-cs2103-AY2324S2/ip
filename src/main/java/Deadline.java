public class Deadline extends Task {
    private final String by;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    public String describe() {
        return String.format("[D]%s (by: %s)",
                super.describe(), this.by
        );
    }

    @Override
    public String toStorageString() {
        return String.format("D,%s,%s", super.toStorageString(), this.by);
    }
}
