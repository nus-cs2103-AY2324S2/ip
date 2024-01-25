/**
 * Class representing a task with an identifier, title, and completion status.
 * Each task has a unique ID, a title, and a boolean flag to mark it as done or
 * not.
 */
class Task {
  private static int count = 0;
  private final int id;
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
    this.id = ++count;
    this.title = title;
    this.isDone = false;
  }

  /**
   * Retrieves the unique ID of the task.
   *
   * @return The ID of the task.
   */
  public int getId() {
    return this.id;
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
}
