import java.time.LocalDate;
class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.getMonth() + " " + from.getDayOfMonth() + " " + from.getYear()
                + " to " + to.getMonth() + " " + to.getDayOfMonth() + " " + to.getYear() + ")";
    }

    public String toFileFormat() {
        String completed = this.isDone ? "1" : "0";
        return "E | " + completed + " | " + this.description + " | " + this.from + " | " + this.to;
    }
}