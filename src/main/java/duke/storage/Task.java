package duke.storage;

/**
 * The Task class defined a task used for the application
 *
 * @author Ryan NgWH
 */
public class Task {
  /**
   * Description of the task
   */
  private String description;

  /**
   * Status of the task (Done/not done)
   */
  private boolean isDone;

  /**
   * Constructor for creating a task
   *
   * @param description Description of the task
   */
  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }

  /**
   * Method to mark the task as done
   */
  public void mark() {
    this.isDone = true;
  }

  /**
   * Method to unmark the task as not done
   */
  public void unmark() {
    this.isDone = false;
  }

  /**
   * String representation of a Task
   *
   * @return String representation of the Task
   */
  @Override
  public String toString() {
    return String.format("[%s] %s", isDone ? "X" : " ", description);
  }
}
