public class Task {
  protected String description;
  protected boolean isDone;

  // instantiate a new Task Object
  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }

  // gets Status icon for the Task itself
  public String getStatusIcon() {
    return (this.isDone ? "[X]" : "[ ]");
  }

  // get status for task
  public String getStatus() {
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
