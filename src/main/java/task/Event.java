package task;

import task.Task;

public class Event extends Task {
  public static final String type = "E";

  private final String startDate;
  private final String endDate;

  public Event(int taskID, String description, String startDate, String endDate) {
    super(taskID, description);
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public Event(int taskID,String description, String startDate, String endDate, boolean isDone) {
    super(taskID, description, isDone);
    this.startDate = startDate;
    this.endDate = endDate;
  }

  @Override
  public String toString() {
    return String.format("[%s]%s (from: %s to: %s)", type, super.toString(), this.startDate, this.endDate);
  }
}
