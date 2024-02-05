import java.util.ArrayList;

public class Ui {
  public static void printWelcomeMessage() {
    System.out.println("    ____________________________________________________________");
    System.out.println("    Hello! I'm Yappy\n    What can I do for you?");
    System.out.println("    ____________________________________________________________");
  }

  public static void printGoodbyeMessage() {
    System.out.println("    ____________________________________________________________");
    System.out.println("    Bye. Hope to see you again soon!");
    System.out.println("    ____________________________________________________________");
  }

  public static void printErrorMessage(String message) {
    System.out.println("    ____________________________________________________________");
    System.out.println("     Error: " + message);
    System.out.println("    ____________________________________________________________");
  }

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

  public static void printMarkAsDoneMessage(Task task) {
    System.out.println("    ____________________________________________________________");
    System.out.println("     Nice! I've marked this task as done:");
    System.out.println("       " + task);
    System.out.println("    ____________________________________________________________");
  }

  public static void printMarkAsUndoneMessage(Task task) {
    System.out.println("    ____________________________________________________________");
    System.out.println("     OK, I've marked this task as not done yet:");
    System.out.println("       " + task);
    System.out.println("    ____________________________________________________________");
  }

  public static void printAddTaskMessage(Task task, ArrayList<Task> tasks) {
    System.out.println("    ____________________________________________________________");
    System.out.println("     Got it. I've added this task:");
    System.out.println("       " + task);
    System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
    System.out.println("    ____________________________________________________________");
  }

  public static void printRemoveTaskMessage(Task task, ArrayList<Task> tasks) {
    System.out.println("    ____________________________________________________________");
    System.out.println("     Noted. I've removed this task:");
    System.out.println("       " + task);
    System.out.println("     Now you have " + (tasks.size() - 1) + " tasks in the list.");
    System.out.println("    ____________________________________________________________");
  }

  public static void printAddedMessage(String input) {
    System.out.println("    ____________________________________________________________");
    System.out.println("    added: " + input);
    System.out.println("    ____________________________________________________________");
  }
}