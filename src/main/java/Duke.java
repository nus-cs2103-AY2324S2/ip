import java.util.Scanner;

public class Duke {

  private static Task[] taskList = new Task[100]; // Array of tasks

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
      } else if (input.startsWith("mark")) {
        int index = Integer.parseInt(input.split(" ")[1]);
        taskList[index - 1].markAsDone();
      } else if (input.startsWith("unmark")) {
        int index = Integer.parseInt(input.split(" ")[1]);
        taskList[index - 1].unmarkAsDone();
      } else {
        addTask(input);
      }
    }
    // Exit Message
    System.out.println("Bye. Hope to see you again soon!");
  }

  /*
   * Prints the list of tasks
   */
  public static void printList(Task[] list) {
    System.out.println("Here are the tasks in your list:");
    for (int i = 0; i < list.length; i++) {
      if (list[i] == null) {
        break;
      }
      System.out.println((i + 1) + ". " + list[i]);
    }
  }

  /*
   * Adds a task to the list
   */
  public static void addTask(String task) {
    for (int i = 0; i < taskList.length; i++) {
      if (taskList[i] == null) {
        taskList[i] = new Task(task);
        System.out.println("added: " + task);
        break;
      }
    }
  }

}
