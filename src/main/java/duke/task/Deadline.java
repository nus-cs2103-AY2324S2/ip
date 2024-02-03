package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
  protected LocalDateTime byDateTime; // Store the deadline date and time

  public Deadline(String description, LocalDateTime byDateTime) {
      super(description);
      this.byDateTime = byDateTime;
  }

  public Deadline(String description, LocalDate byDate) {
      super(description);
      this.byDateTime = byDate.atStartOfDay();
  }

  @Override
  public String toFileString() {
      // Format: D | [Status] | [Description] | [Due Date]
      return "D | " + getStatusnumber() + " | " + description + " | " + byDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
  }

  @Override
  public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("[D]").append(super.toString()).append(" (by: ").append(byDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
  
      if (byDateTime.toLocalTime().getHour() != 0 || byDateTime.toLocalTime().getMinute() != 0) {
          builder.append(" at ").append(byDateTime.format(DateTimeFormatter.ofPattern("hh:mm a")));
      }
  
      builder.append(")");
  
      return builder.toString();
  }
}