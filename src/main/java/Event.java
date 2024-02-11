import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
  protected LocalDateTime start;
  protected LocalDateTime end;

  public Event(String description, LocalDateTime start, LocalDateTime end) {
    super(description, TaskType.EVENT);
    this.start = start;
    this.end = end;
  }

  @Override
  public String toFileFormat() {
    return super.toFileFormat() + " | " + start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + " - " + end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
  }

  @Override
  public String toString() {
    return "[ E ]" + super.toString() + " (from: " + start.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a")) + " to: " + end.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a")) + ")";
  }
}
