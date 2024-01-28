package task;

public class Deadlines extends Task {
    private final String by;

    public Deadlines(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format(
                "[D][%s] %s (by: %s)",
                this.getStatusIcon(), this.description, this.by
        );
    }

    @Override
    public String saveTaskString() {
        return String.format("D | %d | %s | %s", this.isDone ? 1 : 0, this.description, this.by);
    }
}
