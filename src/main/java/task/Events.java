package task;

public class Events extends Task {
    private final String from;
    private final String to;

    public Events(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }


    @Override
    public String toString() {
        return String.format(
                "[E][%s] %s (from: %s to: %s)",
                this.getStatusIcon(), this.description, this.from, this.to
        );
    }

    @Override
    public String saveTaskString() {
        return String.format("E | %d | %s | %s | %s", this.isDone ? 1 : 0, this.description, this.from, this.to);
    }
}
