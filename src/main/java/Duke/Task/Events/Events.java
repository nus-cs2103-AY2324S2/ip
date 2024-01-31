package Duke.Task.Events;

import Duke.Task.Task;

public class Events extends Task {
    protected String from;
    protected String to;

    public Events(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "  [E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toFile() {
        if (isDone) {
            return "E|1|" + description + "|" + from + "|" + to;
        } else {
            return "E|0|" + description + "|" + from + "|" + to;
        }
    }
}
