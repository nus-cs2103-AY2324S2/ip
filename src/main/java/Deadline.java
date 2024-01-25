/**
 * Class representing a Deadline task with a due date.
 * Inherits from the Task class and adds a deadline date.
 */
class Deadline extends Task {
  private String by;

  /**
   * Constructor to create a new Deadline task.
   * Initializes the task with a title and a deadline date.
   *
   * @param title The title of the deadline task.
   * @param by    The deadline date for the task.
   */
  public Deadline(String title, String by) {
    super(title);
    this.by = by;
  }

  /**
   * Provides a string representation of the Deadline task.
   * Format: "[D][\u2713 or \u2718] Title (by: deadline date)" for completed or
   * pending tasks respectively.
   *
   * @return The formatted string representation of the Deadline task.
   */
  @Override
  public String toString() {
    return "[D]" + super.toString() + " (by: " + by + ")";
  }
}
