package charlie.models;

public class Event extends Task {
    protected String startsAt;
    protected String endsAt;

    /**
     * constructor for task Event
     * @param description description of task Event in string form
     * @param startsAt time by which the event starts
     * @param endsAt time by which the event ends
     */
    public Event(String description, String startsAt, String endsAt) {
        super(description);
        this.startsAt = startsAt;
        this.endsAt = endsAt;
    }

    public String getStartsAt() {
        return startsAt;
    }

    public String getEndsAt() {
        return endsAt;
    }

    /**
     * @return description string for task event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startsAt + " to: " + endsAt + ")";
    }

}
