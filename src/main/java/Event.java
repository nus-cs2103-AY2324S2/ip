public class Event extends Task {
    protected String start;
    protected String end;

    /**
     * Creates a Event task with description, start time and end time.
     *
     * @param description Description of the task.
     * @param start Start time of the task.
     * @param end End time of the task.
     */
    public Event(String description, boolean isDone, String start, String end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "E" + super.toString() + " | " + this.start + " - " + this.end;
    }
}
