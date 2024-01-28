package task;

/**
 * Represents a Task.
 * A <code>Task</code> object corresponds to a task with a description and a
 * status.
 * e.g., <code>Task read book</code>
 */
public class Task {
  public int taskID;
  protected final String description;
  protected boolean isDone = false;

  public Task(int taskID, String description) {
    this.taskID = taskID;
    this.description = description;
  }

  public Task(int taskID, String description, boolean isDone) {
    this.taskID = taskID;
    this.description = description;
    this.isDone = isDone;
  }

  public String getDetails() {
    return this.description;
  }

  public String getStatusIcon() {
    return this.isDone ? "X" : " ";
  }

  public void toggleDone() {
    this.isDone = !this.isDone;
  }

  @Override
  public String toString() {
    return String.format("[%s] %s", this.getStatusIcon(), this.getDetails());
  }
}
