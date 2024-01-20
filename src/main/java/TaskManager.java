import java.util.ArrayList;
import exceptions.BadTaskInputException;

public class TaskManager {
  private final ArrayList<Task> tasks;
  public TaskManager() {
    this.tasks = new ArrayList<>();
  }

  public Task addTask(String type, String details) throws BadTaskInputException {
    switch (type) {
      case "todo":
        if (details == null || details.isEmpty()) {
          throw new BadTaskInputException(
            "Details of a todo cannot be empty.",
            "todo <description>",
            "todo read book",
           details
          );
        }

        return this.addTodoTask(details);

      case "deadline":
        String[] deadlineDetails = details.split(" /by ");

        if (deadlineDetails.length < 2) {
          throw new BadTaskInputException(
            "Details of a deadline must include a deadline.",
            "deadline <description> /by <deadline>",
            "deadline return book /by Sunday",
            details
          );
        }

        return this.addDeadlineTask(deadlineDetails[0], deadlineDetails[1]);

      case "event":
        String[] eventDetails = details.split(" /from | /to ");

        if (eventDetails.length < 3) {
          throw new BadTaskInputException(
            "Details of an event must include a start date and an end date.",
            "event <description> /from <start date> /to <end date>",
            "event project meeting /from Mon 2-4pm /to Mon 4-5pm",
            details);
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
   * @param taskNumber the index of the task to be marked as done
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
   * @param taskNumber the index of the task to be unmarked as done
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

  public Task deleteTask(int taskNumber) {
    Task task = this.getTask(taskNumber);
    this.tasks.remove(taskNumber);
    return task;
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
