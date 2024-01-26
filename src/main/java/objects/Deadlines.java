package objects;

public class Deadlines extends Task {
    private final String by;

    public Deadlines(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }

}
