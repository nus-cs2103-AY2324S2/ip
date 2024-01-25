/**
 * Represents the status of a task.
 */
enum TaskStatus {
  DONE("X"),
  UNDONE(" ");

  private final String statusIcon;

  /**
   * Constructs a TaskStatus with the specified status icon.
   * @param statusIcon the status icon associated with the task status
   */
  TaskStatus(String statusIcon) {
    this.statusIcon = statusIcon;
  }

  /**
   * Gets the status icon associated with the task status.
   * @return the status icon
   */
  public String getStatusIcon() {
    return statusIcon;
  }
}