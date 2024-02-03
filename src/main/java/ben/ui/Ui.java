package ben.ui;

import ben.tasks.Task;
import ben.tasks.TaskList;

import java.util.Scanner;

public class Ui {
  private final Scanner in;

  public Ui() {
    this.in = new Scanner(System.in);
  }

  public static void show(String message) {
    System.out.println("      " + message);
  }
  public void showWelcome() {
    show("______________________________________________");
    show("Hello! I'm Ben");
    show("What can I do for you?");
    show("______________________________________________");
  }

  public void showLine() {
    show("______________________________________________");
  }

  public String readCommand() {
    return in.nextLine();
  }

  public void showError(String message) {
    show(message);
  }

  public void showExitMessage() {
    show("Bye. Hope to see you again soon!");
  }

  public void showListMessage() {
    show("Here are the tasks in your list:");
  }

  public void showTask(Task task) {
    show(task.toString());
  }

  public void showTask(TaskList tasks, int index) {
    show(tasks.toString(index));
  }

  public void showTaskList(TaskList tasks) {
    tasks.showTaskList();
  }

  public void showMarkedTaskMessage() {
    show("Nice! I've marked this task as done:");
  }

  public void showUnmarkedTaskMessage() {
    show("OK, I've marked this task as not done yet:");
  }

  public void showAddedTaskMessage() {
    show("Got it. I've added this task:");
  }

  public void showCurrNoOfTasks(TaskList tasks) {
    show("Now you have " + tasks.size() + " tasks in the list.");
  }

  public void showDeletedTaskMessage() {
    show("Noted. I've removed this task:");
  }
}