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
    public Event(String description, Integer priorityNumber,String startsAt, String endsAt) {
        super(description, priorityNumber);

        // Assert that description, startsAt, and endsAt are not null
        assert description != null && !description.trim().isEmpty() : "Description cannot be null or empty";
        assert startsAt != null && !startsAt.trim().isEmpty() : "Start time cannot be null or empty";
        assert endsAt != null && !endsAt.trim().isEmpty() : "End time cannot be null or empty";

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
