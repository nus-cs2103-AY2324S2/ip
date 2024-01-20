import java.util.Scanner;

public class Duke {
  private static String logo = " ____        _        \n"
      + "|  _ \\ _   _| | _____ \n"
      + "| | | | | | | |/ / _ \\\n"
      + "| |_| | |_| |   <  __/\n"
      + "|____/ \\__,_|_|\\_\\___|\n";
  private static String chatbotName = "Aiken Dueet";
  private static TaskManager taskManager = new TaskManager();

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

  private static void addTask(String... inputs) {
    String type = inputs[0];

    StringBuilder details = new StringBuilder();
    for (int i = 1; i < inputs.length; i++) {
      details.append(inputs[i]);
      if (i != inputs.length - 1) {
        details.append(" ");
      }
    }

    try {
      Task task = taskManager.addTask(type, details.toString());
      printIndentedln("Got it. I've added this task:");
      printIndentedln("  " + task);
      printIndentedln(String.format("Now you have %d tasks in the list.", taskManager.getNumberOfTasks()));
    } catch (TaskManager.BadTaskInputException e) {
      printIndentedln(String.format("Something went wrong:\n%s", e.getMessage()));
    }
  }

  private static void listTasks() {
    if (taskManager.getNumberOfTasks() == 0) {
      printIndentedln("No tasks added yet!");
      return;
    }

    int i = 1;
    for (Task task : taskManager.getTasks()) {
      printIndentedln(String.format("%d. %s", i++, task));
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

    if (taskIndex < 0 || taskIndex >= taskManager.getNumberOfTasks()) {
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

    boolean success = taskManager.markTaskAsDone(taskIndex);
    if (!success) {
      printIndentedln("Task is already done!");
      return;
    }

    printIndentedln("Nice! I've marked this task as done:");
    printIndentedln("  " + taskManager.getTask(taskIndex));
  }

  private static void unmarkTaskAsDone(String[] inputs) {
    int taskIndex = validateMarkInputs(inputs);
    if (taskIndex == -1) {
      return;
    }

    boolean success = taskManager.unmarkTaskAsDone(taskIndex);
    if (!success) {
      printIndentedln("Task is not done yet!");
      return;
    }

    printIndentedln("Ok, I've marked this task as not done yet:");
    printIndentedln("  " + taskManager.getTask(taskIndex));
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
          addTask(inputs);
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
