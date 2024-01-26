import java.time.LocalDate;
import java.time.LocalDateTime;

public class Events extends Task {

  LocalDateTime start;

  LocalDateTime end;

  public Events(String description, LocalDateTime start, LocalDateTime end) {
    super(description, "[E]");
    this.start = start;
    this.end = end;
  }

  public LocalDateTime getStart() {return this.start;}

  public LocalDateTime getEnd() {return this.end;}

  @Override
  public String getLogRepresentation() {
    String complete_status = "F";
    if (this.isDone) {complete_status = "T";}
    return "E" + "," + complete_status + "," +
      this.description + ","
      + this.getStart().toString() + "," + this.getEnd().toString();
  }

  @Override
  public String getTimeData() {
    return "(from: " + this.decomposeDateTime(this.start) +
      " to: " + this.decomposeDateTime(this.end) + ")";
  }
}
