package morty;

import java.util.Scanner;

import morty.task.Task;

/**
 * A class that handles Morty's responses to the user.
 */
public class Response {

  Scanner scanner;

  /**
   * Constructs a Ui object.
   */
  public Response() {
    this.scanner = new Scanner(System.in);
  }

  /**
   * Reads the next line of input.
   *
   * @return The next line of input.
   */
  public String readCommand() {
    return scanner.nextLine();
  }

  /**
   * Shows the welcome message.
   */
  public String showWelcome() {
    return "\n /$$      /$$  /$$$$$$  /$$$$$$$  /$$$$$$$$ /$$     /$$\n"
        + "| $$$    /$$$ /$$__  $$| $$__  $$|__  $$__/|  $$   /$$/\n"
        + "| $$$$  /$$$$| $$  \\ $$| $$  \\ $$   | $$    \\  $$ /$$/ \n"
        + "| $$ $$/$$ $$| $$  | $$| $$$$$$$/   | $$     \\  $$$$/  \n"
        + "| $$  $$$| $$| $$  | $$| $$__  $$   | $$      \\  $$/   \n"
        + "| $$\\  $ | $$| $$  | $$| $$  \\ $$   | $$       | $$    \n"
        + "| $$ \\/  | $$|  $$$$$$/| $$  | $$   | $$       | $$    \n"
        + "|__/     |__/ \\______/ |__/  |__/   |__/       |__/    \n"
        + "\nHello I'm\n"
        + "What can I do for you?\n";
  }

  /**
   * Shows the goodbye message.
   */
  public String showGoodbye() {
    return "Bye. Hope to see you again soon!";
  }

  /**
   * Shows the task list.
   *
   * @param taskList The task list.
   */
  public String showTaskList(TaskList taskList) {
    assert taskList.getTasks() != null : "List of tasks cannot be null";
    StringBuilder toReturn = new StringBuilder();
    for (int i = 0; i < taskList.getSize(); i++) {
      toReturn.append((i + 1)).append(". ").append(taskList.get(i).toString()).append("\n");
    }
    return toReturn.toString();
  }

  /**
   * Shows the task added message.
   *
   * @param task The matching task list.
   * @param size The size of the task list.
   */
  public String showTaskAdded(Task task, int size) {
    assert task != null : "Task cannot be null";
    return "Got it. I've added this task:"
        + "\n" + task.toString()
        + "\n" + "Now you have " + size + " tasks in the list.";
  }

  /**
   * Shows the task marked done message.
   *
   * @param task The matching task list.
   */
  public String showTaskMarkedDone(Task task) {
    assert task != null : "Task cannot be null";
    return "Nice! I've marked this task as done:"
        + "\n" + task.toString();
  }

  /**
   * Shows the task removed message.
   *
   * @param task The matching task list.
   * @param size The size of the matching task list.
   */
  public String showTaskRemoved(Task task, int size) {
    assert task != null : "Task cannot be null";
    assert size >= 0 : "Size of task list cannot be negative";
    return "Noted. I've removed this task:\n" + task.toString() + "\nNow you have " + size + " tasks in the list.";
  }

  /**
   * Shows tasks archived message.
   */
  public String showTasksArchived() {
    return "All tasks have been archived.";
  }

  /**
   * Shows the invalid delete message.
   */
  public String showInvalidDelete() {
    return "Invalid delete command.";
  }

  /**
   * Shows the todo usage message.
   */
  public String showTodoUsage() {
    return "Usage: todo <description>";
  }

  /**
   * Shows the deadline usage message.
   */
  public String showDeadlineUsage() {
    return "Usage: deadline <description> /by <date>";
  }

  /**
   * Shows the event usage message.
   */
  public String showEventUsage() {
    return "Usage: event <description> /at <date>";
  }

  /**
   * Shows the find usage message.
   */
  public String showFindUsage() {
    return "Usage: find <keyword>";
  }

  /**
   * Shows the invalid done message.
   */
  public String showInvalidDone() {
    return "Invalid done command.";
  }

  /**
   * Shows the invalid command message.
   */
  public String showInvalidCommand() {
    return "Invalid command.";
  }

  /**
   * Shows the error message.
   */
  public String showErrorMessage(String message) {
    return message;
  }
}
