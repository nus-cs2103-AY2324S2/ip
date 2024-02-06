package tasks;

/**
 * <p>
 *  represents a Event Task that a user would want to save, it has
 *  a description, a startDate and an EndDate.
 *  </p>
 */
public class Event extends Task {
    private final String startDate;
    private final String endDate;
    public Event(String description, String startDate, String endDate) {
        super(description, 'E');
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Event(String description, Boolean isDone, String startDate, String endDate) {
        super(description, isDone,'E');
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (from: %s to: %s)\n", this.startDate, this.endDate);
    }

    @Override
    public String prepareForStorage() {
        return super.prepareForStorage() + String.format(" | %s | %s", this.startDate, this.endDate);
    }
}