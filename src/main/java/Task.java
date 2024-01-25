public abstract class Task {

  private Boolean isCompleted;
  private String name;

  public Task(String name) {
    this.isCompleted = false;
    this.name = name;
  }

  public static final Task makeTask(String taskDetails) {
    String[] components = taskDetails.split(" ");
    String taskName = "";

    if (taskDetails.startsWith("todo")) {

      for (int i = 1; i < components.length; i++) {
        taskName += components[i] + " ";
      }

      return new ToDoTask(taskName.trim());
    } else if (taskDetails.startsWith("deadline")) {

      String deadline = "";

      for (int i = 1; i < components.length; i++) {

        if (components[i].equals("/by")) {

          for (int j = i + 1; j < components.length; j++) {
            deadline += components[j] + " ";
          }
          break;
        } else {
          taskName += components[i] + " ";
        }
      }

      return new DeadlineTask(taskName.trim(), deadline.trim());
    } else if (taskDetails.startsWith("event")) {

      int state = 0;
      String start = "";
      String end = "";

      for (int i = 1; i < components.length; i++) {

        if (components[i].equals("/from")) {
          state = 1;
        } else if (components[i].equals("/to")) {
          state = 2;
        }

        switch (state) {

          case 0:
            taskName += components[i] + " ";
            break;

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

      return new EventTask(taskName.trim(), start.trim(), end.trim());
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
    return String.format(" [%s] %s", this.isCompleted ? "X" : " ", this.name.trim());
  }
}
