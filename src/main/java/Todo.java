/**
 * Class representing a Todo task.
 * Inherits from the Task class and adds specific formatting to the toString
 * method.
 */
class Todo extends Task {
  /**
   * Constructor to create a new Todo task.
   * Calls the constructor of the parent Task class.
   *
   * @param title The title of the todo task.
   */
  public Todo(String title) {
    super(title);
  }

  /**
   * Provides a string representation of the Todo task.
   * Format: "[T][\u2713 or \u2718] Title" for completed or pending tasks
   * respectively.
   *
   * @return The formatted string representation of the Todo task.
   */
  @Override
  public String toString() {
    return "[T]" + super.toString();
  }
}
