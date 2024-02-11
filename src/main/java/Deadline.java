import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
  protected LocalDateTime deadline;

  public Deadline(String description, LocalDateTime deadline) {
    super(description, TaskType.DEADLINE);
    this.deadline = deadline;
  }

  @Override
  public String toFileFormat() {
    return super.toFileFormat() + " | " + deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
  }

  @Override
  public String toString() {
    return "[ D ]" + super.toString() + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a")) + ")";
  }
}
