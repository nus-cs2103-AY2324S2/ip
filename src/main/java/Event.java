public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from.split("from ")[1];
        this.to = to.split("to ")[1];

    }

    @Override
    public String toString() {
        return "E | " + super.toString() + " | " + from + " | " + to;
    }

    public static Event fromString(String input) {
        String[] split = input.split(" \\| ");
        Event event = new Event(split[2], split[3], split[4]);
        if (split[1].equals("X")) {
            event.markAsDone();
        }
        return event;
    }
}