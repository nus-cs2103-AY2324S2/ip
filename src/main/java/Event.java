public class Event extends Task{
    protected String start;
    protected String end;

    public Event (String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }

    @Override
    public String toFileFormat() {
        return "E | " + super.toFileFormat() + " | " + start+ "-" + end;
    }

    public static Task fromFileFormat(String line) throws JayneException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 4 || !"E".equals(parts[0])) {
            throw new JayneException("Invalid line format for Event");
        }

        // The 'from' and 'to' parts are combined in parts[3], so we need to split them
        String[] times = parts[3].split(" to ", 2);
        if (times.length < 2) {
            throw new JayneException("Invalid time format for Event");
        }

        Event task = new Event(parts[2], times[0], times[1]);
        if ("1".equals(parts[1])) {
            task.markAsDone();
        }
        return task;
    }
}
