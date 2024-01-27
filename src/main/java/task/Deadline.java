package task;

import task.Task;

public class Deadline extends Task {
  public static final String type = "D";
  private final java.time.LocalDate deadline;

  public Deadline(int taskID, String description, java.time.LocalDate deadline) {
    super(taskID, description);
    this.deadline = deadline;
  }

  public Deadline(int taskID,  String description, java.time.LocalDate deadline, boolean isDone) {
    super(taskID, description, isDone);
    this.deadline = deadline;
  }

  @Override
  public String toString() {
    return String.format("[%s]%s (by: %s)", type, super.toString(), this.deadline);
  }
}
