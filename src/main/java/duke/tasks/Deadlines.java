package duke.tasks;

import java.time.LocalDateTime;

public class Deadlines extends Task {

  LocalDateTime deadline;

  public Deadlines (String description, LocalDateTime deadline) {
    super(description, "[D]");
    this.deadline = deadline;
  }

  public LocalDateTime getDeadline() {
    return this.deadline;
  }

  @Override
  public String getLogRepresentation() {
    String complete_status = "F";
    if (this.isDone) {complete_status = "T";}
    return "D" + "," + complete_status + ","
      + this.description + "," + this.getDeadline().toString();
  }

  @Override
  public String getTimeData() {
    return "(by: " + this.decomposeDateTime(this.deadline) + ")";
  }

}
