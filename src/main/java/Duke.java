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
    String exitMessage = "Bye. Hope to see you again soon!";
    printIndentedln(exitMessage);
    printHorizontalln();
  }

  private static void addTask(String description) {
    Task task = new Task(description);
    tasks.add(task);

    String taskAddedMessage = "added: " + description;
    printIndentedln(taskAddedMessage);
  }

  private static void listTasks() {
    for (int i = 0; i < tasks.size(); i++) {
      int taskNumber = i + 1;
      String taskDetails = tasks.get(i).getDetails();
      String task = taskNumber + ". " + taskDetails;
      printIndentedln(task);
    }
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

      switch (input) {
        case "list":
          listTasks();
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
