package task;

/**
 * Represents the status of a task.
 */
enum TaskStatus {
  DONE("X"),
  UNDONE(" ");

  private final String statusIcon;

  /**
   * Constructs a TaskStatus with the specified status icon.
   * 
   * @param statusIcon the status icon associated with the task status
   */
  TaskStatus(String statusIcon) {
    this.statusIcon = statusIcon;
  }

  /**
   * Gets the status icon associated with the task status.
   * 
   * @return the status icon
   */
  public String getStatusIcon() {
    return statusIcon;
  }

  /**
   * Returns a Boolean value indicating whether the task is done.
   *
   * @return true if the task is done, false otherwise
   */
  public Boolean isDone() {
    return this == DONE;
  }
}