import java.util.ArrayList;

public class TaskManager {
  private ArrayList<Task> tasks;

  public TaskManager() {
    this.tasks = new ArrayList<>();
  }

  public static class BadTaskInputException extends RuntimeException {
    public BadTaskInputException(String message) {
      super(message);
    }
  }

  public Task addTask(String type, String details) throws BadTaskInputException {
    switch (type) {
      case "todo":
        if (details == null || details.isEmpty()) {
          throw new BadTaskInputException(
              "Details of a todo cannot be empty.\n" +
                  "Format: todo <description>\n" +
                  "Example: todo read book");
        }

        return this.addTodoTask(details);

      case "deadline":
        String[] deadlineDetails = details.split(" /by ");

        if (deadlineDetails.length < 2) {
          throw new BadTaskInputException(
              "Details of a deadline must include a deadline.\n" +
                  "Format: deadline <description> /by <deadline>\n" +
                  "Example: deadline return book /by Sunday");
        }

        return this.addDeadlineTask(deadlineDetails[0], deadlineDetails[1]);

      case "event":
        String[] eventDetails = details.split(" /from | /to ");

        if (eventDetails.length < 3) {
          throw new BadTaskInputException(
              "Details of an event must include a start date and an end date.\n" +
                  "Format: event <description> /from <start date> /to <end date>\n" +
                  "Example: event project meeting /from Mon 2-4pm /to Mon 4-5pm");
        }

        return this.addEventTask(eventDetails[0], eventDetails[1], eventDetails[2]);

      default:
        return this.addTask(type + details);
    }
  }

  public Task addTask(String description) {
    Task task = new Task(description);
    this.tasks.add(task);
    return task;
  }

  public Task addTodoTask(String description) {
    Task task = new Todo(description);
    this.tasks.add(task);
    return task;
  }

  public Task addDeadlineTask(String description, String deadline) {
    Task task = new Deadline(description, deadline);
    this.tasks.add(task);
    return task;
  }

  public Task addEventTask(String description, String startDate, String endDate) {
    Task task = new Event(description, startDate, endDate);
    this.tasks.add(task);
    return task;
  }

  /**
   * Marks the task as done.
   * 
   * @param taskNumber
   * @return true if the task is successfully marked as done, false otherwise
   */
  public boolean markTaskAsDone(int taskNumber) {
    Task task = this.getTask(taskNumber);

    boolean isDone = task.isDone();
    if (isDone) {
      return false;
    }

    task.toggleDone();
    return true;
  }

  /**
   * Unmarks the task as done.
   * 
   * @param taskNumber
   * @return true if the task is successfully unmarked as done, false otherwise
   */
  public boolean unmarkTaskAsDone(int taskNumber) {
    Task task = this.getTask(taskNumber);

    boolean isDone = task.isDone();
    if (!isDone) {
      return false;
    }

    task.toggleDone();
    return true;
  }

  public void deleteTask(int taskNumber) {
    this.tasks.remove(taskNumber);
  }

  public Task getTask(int taskNumber) {
    if (taskNumber < 0 || taskNumber >= this.tasks.size()) {
      return null;
    }

    return this.tasks.get(taskNumber);
  }

  public int getNumberOfTasks() {
    return this.tasks.size();
  }

  public ArrayList<Task> getTasks() {
    return this.tasks;
  }

}
