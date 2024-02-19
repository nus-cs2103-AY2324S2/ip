package baron.models;

/**
 * Event takes in /from and /to parameters, but does not format these dates
 */
public class Event extends Task {

    private String startDate;
    private final String endDate;

    /**
     * Creates an event
     * @param name name of event
     * @param startDate start of event
     * @param endDate end of event
     */
    public Event(String name, String startDate, String endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Creates an event from scratch. Used when retrieving data from database usually.
     * @param id id of event
     * @param name name of event
     * @param isDone whether event is done or not
     * @param startDate event start date
     * @param endDate event end date
     */
    public Event(int id, String name, boolean isDone, String startDate, String endDate) {
        super(id, name, isDone);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    /**
     * Parses data string to create an event object.
     *
     * @param data line
     * @return Created Event object
     */
    public static Event fromDataString(String data) {
        String[] segments = data.split("\\s*\\|\\s*");
        int id = Integer.parseInt(segments[0]);
        boolean done = Long.parseLong(segments[1]) == 1;
        // Strong assumption that there are not | in the data
        String name = segments[2];
        String from = segments[3];
        String to = segments[4];
        Event event = new Event(id, name, done, from, to);
        return event;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toDataString() {
        return super.toDataString() + " | " + this.startDate + " | " + this.endDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startDate + " to: " + endDate + ")";
    }
}
