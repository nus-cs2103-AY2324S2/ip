package tasks;

class Event extends Task {  // default access modifier
    private static final String EVENT_ICON = "[E]";
    private String from;
    private String to;

    Event(String description, String from, String to) {  // default access modifier
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return EVENT_ICON + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
