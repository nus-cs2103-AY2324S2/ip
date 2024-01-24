package tasks;

public class Task {
  private final String description;

  private boolean status;

  public Task(String description) {
    this.description = description;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public boolean getStatus() {
    return this.status;
  }

  @Override
  public String toString() {
    String checkBox = "[ ]";

    if (status)
      checkBox = "[X]";
      
    return checkBox + " " + description;
  }
}
