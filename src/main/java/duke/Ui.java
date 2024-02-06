package duke;

import java.util.ArrayList;

/** Handles user interface interactions such as printing messages */
public class Ui {
  /** Prints a welcome message */
  public static void printWelcomeMessage() {
    System.out.println("    ____________________________________________________________");
    System.out.println("    Hello! I'm Yappy\n    What can I do for you?");
    System.out.println("    ____________________________________________________________");
  }

  /** Prints a goodbye message */
  public static void printGoodbyeMessage() {
    System.out.println("    ____________________________________________________________");
    System.out.println("    Bye. Hope to see you again soon!");
    System.out.println("    ____________________________________________________________");
  }

  /**
   * Prints an error message.
   *
   * @param message The error message to be printed.
   */
  public static void printErrorMessage(String message) {
    System.out.println("    ____________________________________________________________");
    System.out.println("     Error: " + message);
    System.out.println("    ____________________________________________________________");
  }

  /**
   * Prints the list of tasks.
   *
   * @param tasks The list of tasks to be printed.
   */
  public static void printTaskList(ArrayList<Task> tasks) {
    System.out.println("    ____________________________________________________________");
    try {
      if (tasks.isEmpty()) {
        throw new DukeException("Your task list is empty.");
      } else {
        System.out.println("     Here are the tasks in your list");
        for (int i = 1; i <= tasks.size(); i++) {
          System.out.println("       " + i + "." + tasks.get(i - 1));
        }
      }
    } catch (DukeException e) {
      printErrorMessage(e.getMessage());
    }
    System.out.println("    ____________________________________________________________");
  }

  /**
   * Prints a message confirming a task is marked as done.
   *
   * @param task The task that is marked as done.
   */
  public static void printMarkAsDoneMessage(Task task) {
    System.out.println("    ____________________________________________________________");
    System.out.println("     Nice! I've marked this task as done:");
    System.out.println("       " + task);
    System.out.println("    ____________________________________________________________");
  }

  /**
   * Prints a message confirming a task is marked as undone.
   *
   * @param task The task that is marked as undone.
   */
  public static void printMarkAsUndoneMessage(Task task) {
    System.out.println("    ____________________________________________________________");
    System.out.println("     OK, I've marked this task as not done yet:");
    System.out.println("       " + task);
    System.out.println("    ____________________________________________________________");
  }

  /**
   * Prints a message confirming a task is added to the list.
   *
   * @param task The task that is added to the list.
   * @param tasks The list of tasks.
   */
  public static void printAddTaskMessage(Task task, ArrayList<Task> tasks) {
    System.out.println("    ____________________________________________________________");
    System.out.println("     Got it. I've added this task:");
    System.out.println("       " + task);
    System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
    System.out.println("    ____________________________________________________________");
  }

  /**
   * Prints a message confirming a task is removed from the list.
   *
   * @param task The task that is removed from the list.
   * @param tasks The list of tasks.
   */
  public static void printRemoveTaskMessage(Task task, ArrayList<Task> tasks) {
    System.out.println("    ____________________________________________________________");
    System.out.println("     Noted. I've removed this task:");
    System.out.println("       " + task);
    System.out.println("     Now you have " + (tasks.size() - 1) + " tasks in the list.");
    System.out.println("    ____________________________________________________________");
  }

  /**
   * Prints a message confirming a task is added.
   *
   * @param input The input string representing the added task.
   */
  public static void printAddedMessage(String input) {
    System.out.println("    ____________________________________________________________");
    System.out.println("    added: " + input);
    System.out.println("    ____________________________________________________________");
  }

  /**
   * Prints the matching tasks in the list.
   *
   * @param matchingTasks An ArrayList containing the tasks that match the search criteria.
   */
  public static void printMatchingTasks(ArrayList<Task> matchingTasks) {
    System.out.println("    ____________________________________________________________");
    System.out.println("     Here are the matching tasks in your list:");
    for (int i = 0; i < matchingTasks.size(); i++) {
      System.out.println("     " + (i + 1) + "." + matchingTasks.get(i));
    }
    System.out.println("    ____________________________________________________________");
  }
}