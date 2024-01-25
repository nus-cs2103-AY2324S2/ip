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
      try {
        input = scanner.nextLine();
        System.out.println("\n--------------------------------------------------");
        if (input.equals("bye")) {
          scanner.close();
          System.out.println("Bye. Hope to see you again soon!");
        } else if (input.equals("list")) {
          printList(taskList);
        } else if (input.startsWith("mark")) {
          if (input.trim().equals("mark")) {
            throw new DukeException("The index of a task cannot be empty.");
          }
          int index = Integer.parseInt(input.split(" ")[1]);
          if (index > getTaskCount()) {
            throw new DukeException("The index of a task cannot be greater than the number of tasks.");
          }
          if (index <= 0) {
            throw new DukeException("The index of a task cannot be 0 or negative.");
          }
          if (taskList[index - 1] == null) {
            throw new DukeException("The task at index " + index + " does not exist.");
          }
          taskList[index - 1].markAsDone();
        } else if (input.startsWith("unmark")) {
          if (input.trim().equals("unmark")) {
            throw new DukeException("The index of a task cannot be empty.");
          }
          int index = Integer.parseInt(input.split(" ")[1]);
          if (index > getTaskCount()) {
            throw new DukeException("The index of a task cannot be greater than the number of tasks.");
          }
          if (index <= 0) {
            throw new DukeException("The index of a task cannot be 0 or negative.");
          }
          if (taskList[index - 1] == null) {
            throw new DukeException("The task at index " + index + " does not exist.");
          }
          taskList[index - 1].unmarkAsDone();
        } else if (input.startsWith("event")) {
          Event newEvent = Event.createFromInput(input);
          addTask(newEvent);
        } else if (input.startsWith("deadline")) {
          Deadline newDeadline = Deadline.createFromInput(input);
          addTask(newDeadline);
        } else if (input.startsWith("todo")) {
          ToDo newToDo = ToDo.createFromInput(input);
          addTask(newToDo);
        } else {
          throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }

      } catch (DukeException e) {
        e.printErrorMessage();
      }
      System.out.println("--------------------------------------------------\n");
    }
  }

  /*
   * Prints the list of tasks
   */
  private static void printList(Task[] list) {
    System.out.println("Here are the tasks in your list:");
    for (int i = 0; i < list.length; i++) {
      if (list[i] == null) {
        break;
      }
      System.out.println((i + 1) + ". " + list[i]);
    }
  }

  /*
   * Returns the number of tasks in the list
   */
  private static int getTaskCount() {
    int count = 0;
    for (int i = 0; i < taskList.length; i++) {
      if (taskList[i] == null) {
        break;
      }
      count++;
    }
    return count;
  }

  /*
   * Adds a task to the list
   */
  private static void addTask(Task task) {
    System.out.println("Got it. I've added this task:");
    for (int i = 0; i < taskList.length; i++) {
      if (taskList[i] == null) {
        taskList[i] = task;
        System.out.println(task);
        break;
      }
    }
    if (getTaskCount() == 1) {
      System.out.println("Now you have " + getTaskCount() + " task in the list.");
    } else {
      System.out.println("Now you have " + getTaskCount() + " tasks in the list.");
    }
  }

}
