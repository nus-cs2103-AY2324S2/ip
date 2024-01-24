public class Event extends Task {
    private String date_from;
    private String date_to;

    public Event(String description, String date_from, String date_to) {
        super(description);
        this.date_from = date_from;
        this.date_to = date_to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.date_from + " to: " + this.date_to + ")";
    }

}
