import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    public Event(String description, String from, String to, boolean isDone) throws SkylerException {
        super(description, isDone);
        try {
            this.from = LocalDate.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            this.to = LocalDate.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            throw new SkylerException("Invalid date format for event. Please use yyyy-MM-dd.");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) +
                " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}