package task;

import task.Task;

public class Event extends Task {
  public static final String type = "E";

  private final java.time.LocalDate startDate;
  private final java.time.LocalDate endDate;

  public Event(int taskID, String description, java.time.LocalDate startDate, java.time.LocalDate endDate) {
    super(taskID, description);
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public Event(int taskID,String description, java.time.LocalDate startDate, java.time.LocalDate endDate, boolean isDone) {
    super(taskID, description, isDone);
    this.startDate = startDate;
    this.endDate = endDate;
  }

  @Override
  public String toString() {
    return String.format("[%s]%s (from: %s to: %s)", type, super.toString(), this.startDate, this.endDate);
  }
}
