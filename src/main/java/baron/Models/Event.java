package baron.Models;

public class Event extends baron.Models.Task {

    private String startDate;
    private final String endDate;

    public Event(String name, String startDate, String endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Event(int id, String name, boolean done, String startDate, String endDate) {
        super(id, name, done);
        this.startDate = startDate;
        this.endDate = endDate;
    }

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
}
