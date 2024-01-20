import java.util.Scanner;
import exceptions.BadInputException;
import exceptions.UnknownCommandException;

public class Duke {
  private static final String logo = " ____        _        \n"
    + "|  _ \\ _   _| | _____ \n"
    + "| | | | | | | |/ / _ \\\n"
    + "| |_| | |_| |   <  __/\n"
    + "|____/ \\__,_|_|\\_\\___|\n";
  private static final String chatBotName = "Aiken Dueet";
  private static final TaskManager taskManager = new TaskManager();

  private static void printIndentedln(String message) {
    System.out.println("    " + message);
  }

  private static void printHorizontalln() {
    printIndentedln("____________________________________________________________\n");
  }

  private static void greet() {
    printHorizontalln();
    printIndentedln("Hello! I'm " + chatBotName);
    printIndentedln("What can I do for you?");
    printHorizontalln();
  }

  private static void exitMessage() {
    printHorizontalln();
    printIndentedln("Bye. Hope to see you again soon!");
    printHorizontalln();
  }

  private static void addTask(String... inputs) throws BadInputException {
    String type = inputs[0];

    StringBuilder details = new StringBuilder();
    for (int i = 1; i < inputs.length; i++) {
      details.append(inputs[i]);
      if (i != inputs.length - 1) {
        details.append(" ");
      }
    }

    Task task = taskManager.addTask(type, details.toString());

    printIndentedln("Got it. I've added this task:");
    printIndentedln("  " + task);
    printIndentedln(String.format("Now you have %d tasks in the list.", taskManager.getNumberOfTasks()));
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
   * Parses the task number from the inputs.
   *
   * @param inputs the inputs for the mark command
   * @throws BadInputException if the task number is not specified, not an integer, or out of range
   * @return task number
   */
  private static int parseTaskNumber(String[] inputs) throws BadInputException {
    if (inputs.length < 2) {
      throw new BadInputException(
        "Please specify the task number!",
        "mark <task number>",
        "mark 1",
        null
      );
    }

    int taskIndex;

    try {
      int taskNumber = Integer.parseInt(inputs[1]);
      taskIndex = taskNumber - 1;
    } catch (NumberFormatException e) {
      throw new BadInputException(
        "Task number must be an integer!",
        "mark <task number>",
        "mark 1",
        inputs[1]
      );
    }

    if (taskIndex < 0 || taskIndex >= taskManager.getNumberOfTasks()) {
      throw new BadInputException(
        "Task number out of range!",
        "mark <task number>",
        "mark 1",
        inputs[1]
      );
    }

    return taskIndex;
  }

  private static void markTaskAsDone(String[] inputs) {
    int taskIndex = parseTaskNumber(inputs);

    boolean success = taskManager.markTaskAsDone(taskIndex);
    if (!success) {
      printIndentedln("Task is already done!");
      return;
    }

    printIndentedln("Nice! I've marked this task as done:");
    printIndentedln("  " + taskManager.getTask(taskIndex));
  }

  private static void unmarkTaskAsDone(String[] inputs) {
    int taskIndex = parseTaskNumber(inputs);

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

      try {
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
          case "todo":
          case "deadline":
          case "event":
            addTask(inputs);
            break;
          default:
            throw new UnknownCommandException(
              "I'm sorry, but I don't know what that means :-(",
              inputs[0],
              "list", "mark", "unmark", "todo", "deadline", "event", "bye"
            );
        }
      } catch (RuntimeException e) {
        System.out.printf("Something went wrong:\n%s", e.getMessage());
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
