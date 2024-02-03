import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Event extends Task {
  protected LocalDateTime from;
  protected LocalDateTime to;

  public Event(String description, LocalDateTime from, LocalDateTime to) {
      super(description);
      this.from = from;
      this.to = to;
  }
  @Override
  public String toFileString() {
      // Format: E | [Status] | [Description] | [Start Time] - [End Time]
      return "E | " + getStatusnumber() + " | " + description + " | " +
              from.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm")) +
              " - " +
              to.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"));
  }
  @Override
  public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("[E]").append(super.toString()).append(" (from: ").append(from.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
  
      if (from.toLocalTime().getHour() != 0 || from.toLocalTime().getMinute() != 0) {
          builder.append(" at ").append(from.format(DateTimeFormatter.ofPattern("hh:mm a")));
      }
  
      builder.append(" to: ").append(to.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
  
      if (to.toLocalTime().getHour() != 0 || to.toLocalTime().getMinute() != 0) {
          builder.append(" at ").append(to.format(DateTimeFormatter.ofPattern("hh:mm a")));
      }
  
      builder.append(")");
  
      return builder.toString();
  }  
}