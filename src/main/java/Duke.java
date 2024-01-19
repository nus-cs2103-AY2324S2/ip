import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
  private static String logo = " ____        _        \n"
      + "|  _ \\ _   _| | _____ \n"
      + "| | | | | | | |/ / _ \\\n"
      + "| |_| | |_| |   <  __/\n"
      + "|____/ \\__,_|_|\\_\\___|\n";
  private static String chatbotName = "Aiken Dueet";
  private static ArrayList<Task> tasks = new ArrayList<>();

  private static void printIndentedln(String message) {
    System.out.println("    " + message);
  }

  private static void printHorizontalln() {
    printIndentedln("____________________________________________________________\n");
  }

  private static void greet() {
    printHorizontalln();
    printIndentedln("Hello! I'm " + chatbotName);
    printIndentedln("What can I do for you?");
    printHorizontalln();
  }

  private static void exitMessage() {
    printHorizontalln();
    printIndentedln("Bye. Hope to see you again soon!");
    printHorizontalln();
  }

  private static void addTask(String description) {
    Task task = new Task(description);
    tasks.add(task);

    String taskAddedMessage = "added: " + description;
    printIndentedln(taskAddedMessage);
  }

  private static void listTasks() {
    if (tasks.size() == 0) {
      printIndentedln("No tasks added yet!");
      return;
    }

    for (int i = 0; i < tasks.size(); i++) {
      int taskNumber = i + 1;
      printIndentedln(String.format("%d. %s", taskNumber, tasks.get(i)));
    }
  }

  /**
   * Validates the inputs for the mark command and return the task number if
   * valid.
   * 
   * @param inputs
   * @return task number if valid, -1 otherwise
   */
  private static int validateMarkInputs(String[] inputs) {
    if (inputs.length < 2) {
      printIndentedln("Please specify the task number!");
      return -1;
    }

    int taskIndex;

    try {
      int taskNumber = Integer.parseInt(inputs[1]);
      taskIndex = taskNumber - 1;
    } catch (NumberFormatException e) {
      printIndentedln("Invalid task number!");
      return -1;
    }

    if (taskIndex < 0 || taskIndex >= tasks.size()) {
      printIndentedln("Task number out of range!");
      return -1;
    }

    return taskIndex;
  }

  private static void markTaskAsDone(String[] inputs) {
    int taskIndex = validateMarkInputs(inputs);
    if (taskIndex == -1) {
      return;
    }

    Task task = tasks.get(taskIndex);

    boolean isDone = task.isDone();
    if (isDone) {
      printIndentedln("Task is already done!");
      return;
    }

    task.toggleDone();

    printIndentedln("Nice! I've marked this task as done:");
    printIndentedln("  " + task);
  }

  private static void unmarkTaskAsDone(String[] inputs) {
    int taskIndex = validateMarkInputs(inputs);
    if (taskIndex == -1) {
      return;
    }

    Task task = tasks.get(taskIndex);

    boolean isDone = task.isDone();
    if (!isDone) {
      printIndentedln("Task is already not done!");
      return;
    }

    task.toggleDone();

    printIndentedln("Ok, I've marked this task as not done yet:");
    printIndentedln("  " + task);
  }

  private static void REPL() {
    Scanner sc = new Scanner(System.in);

    while (true) {
      String input = sc.nextLine();

      if (input.equals("bye")) {
        sc.close();
        break;
      }

      printHorizontalln();

      String[] inputs = input.split(" ", 2);

      switch (inputs[0]) {
        case "list":
          listTasks();
          break;
        case "mark":
          markTaskAsDone(inputs);
          break;
        case "unmark":
          unmarkTaskAsDone(inputs);
          break;
        default:
          addTask(input);
          break;
      }

      printHorizontalln();
    }
  }

  public static void main(String[] args) {
    System.out.println(logo);

    greet();

    REPL();

    exitMessage();
  }
}
