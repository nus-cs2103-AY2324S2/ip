package morty;

import java.util.Scanner;

import morty.task.Task;

public class Ui {

  Scanner scanner;

  /**
   * Constructs a Ui object.
   */
  public Ui() {
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
  public void showWelcome() {
    System.out.println(
        "\n /$$      /$$  /$$$$$$  /$$$$$$$  /$$$$$$$$ /$$     /$$\n"
            + "| $$$    /$$$ /$$__  $$| $$__  $$|__  $$__/|  $$   /$$/\n"
            + "| $$$$  /$$$$| $$  \\ $$| $$  \\ $$   | $$    \\  $$ /$$/ \n"
            + "| $$ $$/$$ $$| $$  | $$| $$$$$$$/   | $$     \\  $$$$/  \n"
            + "| $$  $$$| $$| $$  | $$| $$__  $$   | $$      \\  $$/   \n"
            + "| $$\\  $ | $$| $$  | $$| $$  \\ $$   | $$       | $$    \n"
            + "| $$ \\/  | $$|  $$$$$$/| $$  | $$   | $$       | $$    \n"
            + "|__/     |__/ \\______/ |__/  |__/   |__/       |__/    \n");
    System.out.println("\nHello I'm\n");
    System.out.println("What can I do for you?\n");
  }

  /**
   * Shows the goodbye message.
   */
  public void showGoodbye() {
    System.out.println("Bye. Hope to see you again soon!");
  }

  /**
   * Shows the loading error message.
   */
  public void showLoadingError() {
    System.out.println("Error loading file. Creating new task list.");
  }

  /**
   * Shows the line.
   */
  public void showLine() {
    System.out.println("\n============================================================\n");
  }

  /**
   * Shows the error message.
   *
   * @param message The error message.
   */
  public void showError(String message) {
    System.err.println(message);
  }

  /**
   * Shows the task list.
   *
   * @param taskList The task list.
   */
  public void showTaskList(TaskList taskList) {
    for (int i = 0; i < taskList.getSize(); i++) {
      System.out.println((i + 1) + ". " + taskList.get(i).toString());
    }
  }

  /**
   * Shows the matching task list.
   *
   * @param taskList The matching task list.
   */
  public void showTaskAdded(Task task, int size) {
    System.out.println("Got it. I've added this task:");
    System.out.println(task.toString());
    System.out.println("Now you have " + size + " tasks in the list.");
  }

  /**
   * Shows the matching task list.
   *
   * @param taskList The matching task list.
   */
  public void showTaskMarkedDone(Task task) {
    System.out.println("Nice! I've marked this task as done:");
    System.out.println(task.toString());
  }

  /**
   * Shows the matching task list.
   *
   * @param taskList The matching task list.
   */
  public void showTaskRemoved(Task task, int size) {
    System.out.println("Noted. I've removed this task:");
    System.out.println(task.toString());
    System.out.println("Now you have " + size + " tasks in the list.");
  }

  /**
   * Shows the matching task list.
   *
   * @param taskList The matching task list.
   */
  public void showInvalidDelete() {
    System.out.println("Invalid delete command.");
  }

  /**
   * Shows the matching task list.
   *
   * @param taskList The matching task list.
   */
  public void showTodoUsage() {
    System.out.println("Usage: todo <description>");
  }

  /**
   * Shows the matching task list.
   *
   * @param taskList The matching task list.
   */
  public void showDeadlineUsage() {
    System.out.println("Usage: deadline <description> /by <date>");
  }

  /**
   * Shows the matching task list.
   *
   * @param taskList The matching task list.
   */
  public void showEventUsage() {
    System.out.println("Usage: event <description> /at <date>");
  }

  /**
   * Shows the matching task list.
   *
   * @param taskList The matching task list.
   */
  public void showInvalidDone() {
    System.out.println("Invalid done command.");
  }

  /**
   * Shows the matching task list.
   *
   * @param taskList The matching task list.
   */
  public void showInvalidCommand() {
    System.out.println("Invalid command.");
  }
}