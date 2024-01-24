import java.util.ArrayList;
import java.util.Scanner;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

public class Duke {
  private final static String indentation = " ".repeat(3);
  private final static String subIndentation = indentation + " ";
  private final static String divider = "_".repeat(60);
  private final static String logo = " _               _          \n" +
      "    | |   _   _  ___| | ___   _ \n" +
      "    | |  | | | |/ __| |/ / | | |      |\\__/,|   (`\\\n" +
      "    | |__| |_| | (__|   <| |_| |    _.|o o  |_   ) )\n" +
      "    |_____\\__,_|\\___|_|\\_\\\\__, |  -(((---(((--------\n" +
      "                          |___/ ";

  public static void main(String[] args) {
    printOutput(logo, "Hello! I'm Lucky the cat", "What can I do for you?");
    Scanner sc = new Scanner(System.in);
    ArrayList<Task> tasks = new ArrayList<>();
    boolean isChatting = true;
    Command command;

    while (isChatting) {
      String[] input = sc.nextLine().split(" ", 2);

      command = Command.parseCommand(input[0]);

      switch (command) {
        case VIEW_LIST:
          printList(tasks);
          break;
        case EXIT:
          exit();
          break;
        case SET_MARK:
          updateMarkStatus(true, tasks, input);
          break;
        case SET_UNMARK:
          updateMarkStatus(false, tasks, input);
          break;
        case INSERT_TODO:
          insertToDo(input, tasks);
          break;
        case INSERT_DEADLINE:
          insertDeadline(input, tasks);
          break;
        case INSERT_EVENT:
          insertEvent(input, tasks);
          break;
        default:
          break;
      }
    }
    sc.close();
  }

  public static void printOutput(String... msg) {
    System.out.println(indentation + divider);

    for (String string : msg) {
      System.out.println(subIndentation + string);
    }
    System.out.println(indentation + divider + "\n");
  }

  public static void printList(ArrayList<Task> tasks) {
    StringBuilder sb = new StringBuilder();
    int i = 1;
    for (Task task : tasks) {
      sb.append(i + "." + task.toString() + "\n" + subIndentation);
      i++;
    }
    printOutput("Here are the tasks in your list:", sb.toString());
  }

  public static void exit() {
    printOutput("Goodbye my friend. See you soon!");
    System.exit(0);
  }

  public static void updateMarkStatus(boolean isMark, ArrayList<Task> tasks, String[] input) {

    int index = Integer.parseInt(input[1]) - 1;

    // check if index is within bounds
    if (index >= tasks.size()) {
      printOutput("Task not found!");
      return;
    }

    if (isMark) {
      // check if there's no change in status
      if (tasks.get(index).getStatus()) {
        printOutput("The task was already marked as done. I'm not changing anything.");
      } else {
        tasks.get(index).setStatus(true);
        printOutput("Nice! I've marked this task as done:",
            tasks.get(index).toString());
      }
    } else {
      // check if there's no change in status
      if (!tasks.get(index).getStatus()) {
        printOutput("The task you're unmarking was not marked to begin with... I'm not changing anything.");
      } else {
        tasks.get(index).setStatus(false);
        printOutput("OK, I've marked this task as not done yet: ",
            tasks.get(index).toString());
      }
    }
  }

  public static void insertToDo(String[] input, ArrayList<Task> tasks) {
    ToDo todoTask = new ToDo(input[1]);
    printOutput("Got it. I've added this task:", indentation +
        todoTask.toString(),
        "Now you have " + (tasks.size() + 1) + " tasks in the list.");
    tasks.add(todoTask);
  }

  // TODO 1. missing /by
  public static void insertDeadline(String[] input, ArrayList<Task> tasks) {
    String[] deadlineDetails = input[1].split("/by");
    Deadline deadlineTask = new Deadline(deadlineDetails[0].trim(), deadlineDetails[1].trim());
    printOutput("Got it. I've added this task:", indentation + deadlineTask.toString(),
        "Now you have " + (tasks.size() + 1) + " tasks in the list.");
    tasks.add(deadlineTask);
  }

  // TODO 1. missing /from /to
  // 2. should it always be /from followed by /to?
  public static void insertEvent(String[] input, ArrayList<Task> tasks) {
    String[] eventDetails = input[1].split("/from|/to");
    Event eventTask = new Event(eventDetails[0].trim(), eventDetails[1].trim(), eventDetails[2]);
    printOutput("Got it. I've added this task:", indentation + eventTask.toString(),
        "Now you have " + (tasks.size() + 1) + " tasks in the list.");
    tasks.add(eventTask);
  }
}
