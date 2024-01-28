public class Event extends Task {
    private final DateTime startDateTime;
    private final DateTime endDateTime;
    public Event(String description, DateTime startDateTime, DateTime endDateTime) {
        super(description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + this.startDateTime.formatDateTime() + " to: " + this.endDateTime.formatDateTime() +")";
    }

    @Override
    public String serializeTask() {
        return "E | " + super.serializeTask() + " | " + this.startDateTime.serializeDateTime() + " - " + this.endDateTime.serializeDateTime();
    }
}
