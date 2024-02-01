import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {

  private LocalDate startDate;
  private String startTiming;
  private LocalDate endDate;
  private String endTiming;

  public EventTask(String name, String start, String end) {
    super(name);

    String[] startDateTime = start.split(" ");
    this.startDate = LocalDate.parse(startDateTime[0]);
    this.startTiming = startDateTime[1];
    String[] endDateTime = end.split(" ");
    this.endDate = LocalDate.parse(endDateTime[0]);
    this.endTiming = endDateTime[1];
  }

  @Override
  public String toString() {
    return String.format("[E]%s (from: %s %s to: %s %s)", super.toString(),
        this.startDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")), this.startTiming,
        this.endDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")), this.endTiming);
  }
}
