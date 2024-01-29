public class Event extends Task {
    protected String date;
    protected String time;

    public Event(String description, String date, String time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return "[" + super.getStatusIcon() + "]" + " Event | " + this.description
                + " | " + this.date
                + " - " + this.time;
    }
}