package ui;

import task.Task;

public class Ui {
  public void showWelcomeMessage() {
    String botName = "GeePeeTee";
    System.out.println("Hello! I'm " + botName + "\nWhat can I do for you?");
  }

  public void showGoodbyeMessage() {
    System.out.println("Bye. Hope to see you again soon!");
  }

  public void showLoadingError() {
    System.out.println("Error loading the task list file.");
  }

  public void showFileNotFoundError() {
    System.out.println("File not found.");
  }

  public void showErrorMessage(String message) {
    System.out.println("Oops! An error occurred:");
    System.out.println(message);
  }

  public void showTaskMessage(Task task) {
    System.out.println(task);
  }

  public void showTaskUnmarked(Task task) {
    System.out.println("OK, I've marked this task as not done yet:");
    showTaskMessage(task);
  }

  public void showTaskMarked(Task task) {
    System.out.println("Nice! I've marked this task as done:");
    showTaskMessage(task);
  }

  public void showAddTask(Task task, int taskCount) {
    System.out.println("Got it. I've added this task:");
    showTaskMessage(task);
    System.out.println(getTaskCountMessage(taskCount));
  }

  public void showDeleteTask(Task task, int taskCount) {
    System.out.println("Noted. I've removed this task:");
    showTaskMessage(task);
    System.out.println(getTaskCountMessage(taskCount));
  }

  public void showListOfCommands() {
    System.out.println("Here are the available commands:");
    System.out.println("1. todo <description>: Add a todo task");
    System.out.println("2. deadline <description> /by <date>: Add a deadline task");
    System.out.println("3. event <description> /at <date>: Add an event task");
    System.out.println("4. list: List all tasks");
    System.out.println("5. mark <taskNumber>: Mark a task as done");
    System.out.println("6. unmark <taskNumber>: Mark a task as not done");
    System.out.println("7. delete <taskNumber>: Remove a task");
    System.out.println("8. bye: Exit the program");
  }

  private String getTaskCountMessage(int taskCount) {
    if (taskCount == 1) {
      return "Now you have " + taskCount + " task in the list.";
    } else {
      return "Now you have " + taskCount + " tasks in the list.";
    }
  }
}
