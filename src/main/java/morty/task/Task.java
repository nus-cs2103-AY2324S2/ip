package morty.task;

/**
 * Class representing a task with an identifier, title, and completion status.
 * Each task has a unique ID, a title, and a boolean flag to mark it as done or
 * not.
 */
public abstract class Task {
  private String title;
  private boolean isDone;

  /**
   * Constructor to create a new task.
   * Initializes the task with a unique ID and the specified title.
   * The task is initially marked as not done.
   *
   * @param title The title of the task.
   */
  public Task(String title) {
    this.title = title;
    this.isDone = false;
  }

  /**
   * Constructor to create a new task.
   * Initializes the task with a unique ID, the specified title and completion
   * status.
   *
   * @param title The title of the task.
   * @param isDone The completion status of the task.
   */
  public Task(String title, boolean isDone) {
    this.title = title;
    this.isDone = isDone;
  }

  /**
   * Returns the completion status of the task.
   *
   * @return True if the task is done, false otherwise.
   */
  public String getTitle() {
    return title;
  }

  /**
   * Marks the task as done by setting its completion status to true.
   */
  public void markDone() {
    this.isDone = true;
  }

  /**
   * Provides a string representation of the task.
   * Format: "[\u2713] Title" for completed tasks, "[\u2718] Title" for pending
   * tasks.
   *
   * @return The formatted string representation of the task.
   */
  @Override
  public String toString() {
    return (isDone ? "[\u2713] " : "[\u2718] ") + title;
  }

  /**
   * Provides a string representation of the task for storage.
   * Format: "0 | Title" for pending tasks, "1 | Title" for completed tasks.
   *
   * @return The formatted string representation of the task for storage.
   */
  public String serialize() {
    return (this.isDone ? "1" : "0") + " | " + title;
  }
}
