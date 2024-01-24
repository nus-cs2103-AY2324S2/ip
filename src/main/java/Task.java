
public abstract class Task {

  private Boolean isCompleted;
  private String name;

  public Task(String name) {
    this.isCompleted = false;
    this.name = name;
  }

  public static final Task makeTask(String taskDetails) {
    String[] components = taskDetails.split(" ");

    if (taskDetails.startsWith("todo") && components.length == 2) {
      return new ToDoTask(components[1]);
    } else if (taskDetails.startsWith("deadline")) {

      for (int i = 0; i < components.length; i++) {
        if (components[i].equals("/by")) {
          String deadline = "";
          for (int j = i + 1; j < components.length; j++) {
            deadline += components[j] + " ";
          }
          return new DeadlineTask(components[1], deadline.trim());
        }
      }
    } else if (taskDetails.startsWith("event")) {

      int state = 0;
      String start = "";
      String end = "";

      for (int i = 0; i < components.length; i++) {

        if (components[i].equals("/from")) {
          state = 1;
        } else if (components[i].equals("/to")) {
          state = 2;
        }

        switch (state) {
          case 1:
            if (!components[i].equals("/from")) {
              start += components[i] + " ";
            }
            break;

          case 2:
            if (!components[i].equals("/to")) {
              end += components[i] + " ";
            }
            break;

          default:
            break;
        }
      }

      return new EventTask(components[1], start.trim(), end.trim());
    }

    // Invalid task description
    return null;
  }

  public void markAsComplete() {
    this.isCompleted = true;
  }

  public void markAsIncomplete() {
    this.isCompleted = false;
  }

  @Override
  public String toString() {
    return String.format(" [%s] %s", this.isCompleted ? "X" : " ", this.name);
  }
}
