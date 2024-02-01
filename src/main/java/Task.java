public class Task {
  protected String description;
  protected boolean isDone;

  // instantiate a new Task Object
  public Task(boolean isDone, String description) {
    this.description = description;
    this.isDone = isDone;
  }

  // gets Status icon for the Task itself
  public String getStatusIcon() {
    return (this.isDone ? "[X]" : "[ ]");
  }

  public String saveTask() {
    return (this.isDone ? "1" : "0") + " | " + this.description;
  }

  // get status for task
  @Override
  public String toString() {
    return this.getStatusIcon() + " " + this.description;
  }

  // mark task
  public void markTask() {
    this.isDone = true;
  }

  // unmark task
  public void unmarkTask() {
    this.isDone = false;
  }
}
