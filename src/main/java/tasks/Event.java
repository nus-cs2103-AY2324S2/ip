package tasks;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String[] parts) {
        super(parts[0]);
        this.type = TaskType.EVENT;
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid input for event task. Expected: <description> /at <from> <to>");
        }
        try {
            String fromString = parts[1].substring(5).trim();
            String toString = parts[2].substring(3);
            this.from = Parser.parseDate(fromString);
            this.to = Parser.parseDate(toString);
        } catch (Exception e) {
            System.out.println("Error parsing LocalDateTime: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy hh mm")) + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy hh mm")) + ")";
    }

    @Override
    public String formattedString() {
        return "E" + super.formattedString() + "|" + from.toString() + "|" + to.toString();
    }

    @Override
    public String getType() {
        return type.toString();
    }
}
