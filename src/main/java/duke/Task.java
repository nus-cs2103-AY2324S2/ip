package duke;

/** Represents a task in the task list */
public class Task {
  protected String description;
  protected boolean isDone;

  /**
   * Constructs a Task object with the specified description.
   *
   * @param description The description of the task.
   */
  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }

  /**
   * Returns the status icon representing whether the task is done ("X") or not (" ").
   *
   * @return The status icon.
   */
  public String getStatusIcon() {
    return (isDone ? "X" : " ");
  }

  /** Marks the task as done ("X") */
  public void markAsDone() {
    this.isDone = true;
  }

  /** Marks the task as undone (" ") */
  public void markAsUndone() {
    this.isDone = false;
  }

  /**
   * Returns a string representation of the Task object.
   *
   * @return A string representation of the Task object.
   */
  @Override
  public String toString() {
    return "[" + getStatusIcon() + "] " + description;
  }

  /**
   * Returns a string formatted for saving the Task object to duke.txt.
   *
   * @return A string formatted for saving the Task object to duke.txt.
   */
  public String toFileString() {
    return String.format("%d | %s", isDone ? 1 : 0, description);
  }
}