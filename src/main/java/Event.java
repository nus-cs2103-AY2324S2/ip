/**
 * Represents an Event task, which is a type of task that needs to be completed.
 * Inherits from the Task class.
 */
class Event extends Task {
    private String from;
    private String to;

    Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from, to);
    }
}