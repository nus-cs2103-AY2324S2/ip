package duke.task;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Returns the event in a new String format when written in a file.
     *
     * @return New String format.
     */
    @Override
    public String outputString() {
        return "E | " + super.outputString() + " | " + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at +  ")";
    }
}
