package duke.task;

public class Task {
  protected String description;
  protected boolean isDone;

  public Task(String description) {
      this.description = description;
      this.isDone = false;
  }

  public void markAsDone() {
      this.isDone = true;
  }

  public void unmarkAsDone() {
      this.isDone = false;
  }

  public String getStatusnumber() {
      return (isDone ? "1" : "0"); // Use "1" for done, "0" for not done
  }

  public String getStatusIcon() {
    return (isDone ? "[X]" : "[ ]"); 
  }

  public String toFileString() {
      // Format: [TaskType] | [Status] | [Description]
      return ""; // Implement this in each subclass (duke.task.Todo, duke.task.Deadline, duke.task.Event)
  }
  
  @Override
  public String toString() {
      return getStatusIcon() + " " + description;
  }
}

