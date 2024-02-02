package baron.Models;

public class Event extends baron.Models.Task {

    private String startDate;
    private String endDate;

    public Event (String name, String startDate, String endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Event (int id, String name, boolean done, String startDate, String endDate) {
        super(id, name, done);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static Event fromDataString (String data) {
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

    public String getStartDate () {
        return this.startDate;
    }

    public void setStartDate (String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate () {
        return this.endDate;
    }

    public void setEndDate (String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString () {
        return "[E]" + super.toString() + " (from: " + startDate + " to: " + endDate + ")";
    }

    public String toDataString () {

        return super.toDataString() + " | " + this.startDate + " | " + this.endDate;
    }
}
