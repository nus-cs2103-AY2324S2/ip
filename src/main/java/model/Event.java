package model;

public class Event extends Task{
    private String from;
    private String to;

    public Event(String label, String from, String to) {
        super(label);
        this.from = formatDate(from);
        this.to = formatDate(to);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from, to);
    }

    /**
     * Returns a parsable string of the event task. Meant to be used for storage purposes.
     *
     * @return a parsable string representation of the task and all its details
     */
    @Override
    public String parsableString() {
        return String.format("E|%s|%s|%s", super.parsableString(), from, to);
    }
}
