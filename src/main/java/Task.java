public class Task {
  protected String description;
  protected boolean isDone;

  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }

  public String getStatusIcon() {
    return (isDone ? "X" : " "); // mark done task with X
  }

  public String getDetails() {
    return String.format("[%s] %s", this.getStatusIcon(), this.description);
  }

  public void setComplete() {
    this.isDone = true;
  }

  public void setIncomplete() {
    this.isDone = false;
  }

  public void mark() {
    this.setComplete();
    System.out.println("\tNice! I've marked this task as done:\n\t" + this.getDetails());
  }

  public void unmark() {
    this.setIncomplete();
    System.out.println("\tOK, I've marked this task as not done yet:\n\t" + this.getDetails());
  }

  @Override
  public String toString() {
    return this.getDetails();
  }
}
