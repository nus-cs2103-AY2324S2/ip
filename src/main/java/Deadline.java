import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
  protected LocalDate by;

  public Deadline(boolean isDone, String description, LocalDate by) {
    super(isDone, description);
    this.by = by;
  }

  @Override
  public String saveTask() {
    return "D | " + super.saveTask() + " | " + this.by;
  }

  @Override
  public String toString() {
    String formattedDate = this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    return "[D]" + super.toString() + " (by: " + formattedDate + ")";
  }
}
