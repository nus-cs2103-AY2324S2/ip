public class Event extends Task{

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + (isDone ? "[X] " : "[ ] ") + super.description + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toFileString() {
        return "E" + " | " + (isDone ? "1" : "0") + " | " + description + " | " +  from + " - " + to;
    }

    public static Event fromFileString(String str) {
        String[] parts = str.split(" \\| ");
        if (!parts[0].equals("E")) {
            return null; // or throw an exception
        }
        String description = parts[2].trim();
        String[] times = parts[3].split(" - ");
        if (times.length < 2) {
            return null;
        }
        String from = times[0].trim();
        String to = times[1].trim();
        boolean isDone = parts[1].trim().equals("1");
        Event event = new Event(description, from, to);
        if (isDone) event.markAsDone();
        return event;
    }

}
