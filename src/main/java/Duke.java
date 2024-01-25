import java.util.Scanner;

public class Duke {

  private static String[] taskList = new String[100]; // List of tasks

  public static void main(String[] args) {
    String input = "";
    String botName = "GeePeeTee";
    // Welcome Message
    System.out.println("Hello! I'm " + botName + "\nWhat can I do for you?");
    Scanner scanner = new Scanner(System.in);
    while (!input.equals("bye")) {
      input = scanner.nextLine();
      if (input.equals("bye")) {
        scanner.close();
        break;
      } else if (input.equals("list")) {
        printList(taskList);
      } else {
        addTask(input);
        System.out.println("added: " + input);
      }
    }
    // Exit Message
    System.out.println("Bye. Hope to see you again soon!");
  }

  /*
   * Prints the list of tasks
   */
  public static void printList(String[] list) {
    for (int i = 0; i < list.length; i++) {
      if (list[i] != null) {
        System.out.println(i + 1 + ". " + list[i]);
      }
    }
  }

  /*
   * Adds a task to the taskList
   */
  private static void addTask(String task) {
    for (int i = 0; i < taskList.length; i++) {
      if (taskList[i] == null) {
        taskList[i] = task;
        break;
      }
    }
  }
}
