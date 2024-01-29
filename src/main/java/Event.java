public class Event extends Task {
    protected String start;
    protected String end;

    public Event(String taskName, String start, String end) {
        super(taskName);
        this.start = start;
        this.end = end;
    }

    private String getTypeIcon() {
        return "[E]";
    }

    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + taskName + " | " + start + " to " + end;
    }

    public static Event fromFileFormat(String fileFormat) throws TaskException {
        String[] parts = fileFormat.split(" \\| ");
        String[] times = parts[3].split(" to ");
        Event event = new Event(parts[2], times[0], times[1]);
        if (parts[1].equals("1")) {
            event.check();
        }
        return event;
    }

    @Override
    public String toString() {
        return getTypeIcon() + super.getStatusIcon() + " " + taskName +
                " (from: " + start + " to: " + end + ")";
    }
}
