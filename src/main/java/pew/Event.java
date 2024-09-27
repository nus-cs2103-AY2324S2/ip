package pew;

public class Event extends Task {
    protected String type = "E";
    protected String start;
    protected String end;

    /**
     * Constructor for Event
     *
     * @param index index of the task
     * @param description description of the task
     * @param start start date and time of the task
     * @param end end date and time of the task
     */
    public Event(int index, String description, String start, String end) {
        super(index, description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String getTask() {
        return index + ". [" + type + "][" + getStatusIcon() + "] " + description + " (from: " + start + " to: " + end + ")";
    }

    @Override
    public String save() {
        return type + "|" + (isDone ? "1" : "0") + "|" + description + "|" + start + "|" + end;
    }
}
