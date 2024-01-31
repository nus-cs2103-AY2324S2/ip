package checkbot.task;

public class Deadline extends Task {
    private final String to;

    public Deadline(String name, String to) {
        super(name);
        this.to = tryParseDate(to);
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + this.to + ")";
    }

    @Override
    public String formatForFile() {
        return String.format("D | %s | %s", super.formatForFile(), this.to);
    }
}
