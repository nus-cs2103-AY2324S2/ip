import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

  private static ArrayList<Task> taskList = new ArrayList<>();// Array of tasks

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
        }
        processInput(input);

      } catch (DukeException e) {
        e.printErrorMessage();
      }
      System.out.println("--------------------------------------------------\n");
    }
  }

  /*
   * Process input
   */
  private static void processInput(String input) throws DukeException {
    if (input.equals("list")) {
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
      if (taskList.get(index - 1) == null) {
        throw new DukeException("The task at index " + index + " does not exist.");
      }
      taskList.get(index - 1).markAsDone();
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
      if (taskList.get(index - 1) == null) {
        throw new DukeException("The task at index " + index + " does not exist.");
      }
      taskList.get(index - 1).unmarkAsDone();
    } else if (input.startsWith("delete")) {
      if (input.trim().equals("delete")) {
        throw new DukeException("The index of a task cannot be empty.");
      }
      int index = Integer.parseInt(input.split(" ")[1]);
      if (index > getTaskCount()) {
        throw new DukeException("The index of a task cannot be greater than the number of tasks.");
      }
      if (index <= 0) {
        throw new DukeException("The index of a task cannot be 0 or negative.");
      }
      if (taskList.get(index - 1) == null) {
        throw new DukeException("The task at index " + index + " does not exist.");
      }
      removeTask(index);

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
  }

  /*
   * Prints the list of tasks
   */
  private static void printList(ArrayList<Task> list) {
    System.out.println("Here are the tasks in your list:");
    for (int i = 0; i < list.size(); i++) {
      System.out.println((i + 1) + ". " + list.get(i));
    }
  }

  /*
   * Returns the number of tasks in the list
   */
  private static int getTaskCount() {
    return taskList.size();
  }

  /*
   * Return number of task message
   */
  private static String getTaskCountMessage() {
    if (getTaskCount() == 1) {
      return "Now you have " + getTaskCount() + " task in the list.";
    } else {
      return "Now you have " + getTaskCount() + " tasks in the list.";
    }
  }

  /*
   * Adds a task to the list
   */
  private static void addTask(Task task) {
    System.out.println("Got it. I've added this task:");
    taskList.add(task);
    System.out.println(task);
    System.out.println(getTaskCountMessage());
  }

  /*
   * Removes a task from the list
   */
  private static void removeTask(int index) {
    System.out.println("Noted. I've removed this task:");
    System.out.println(taskList.get(index - 1));
    taskList.remove(index - 1);
    System.out.println(getTaskCountMessage());
  }

}
