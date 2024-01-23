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
    // ArrayList<String> tasks = new ArrayList<>();
    // ArrayList<Boolean> taskStatus = new ArrayList<>();
    ArrayList<Task> tasks = new ArrayList<>();
    boolean isChatting = true;
    String tick = "[X]";
    String untick = "[ ]";
    String checkBox = untick;
    Command command;

    while (isChatting) {
      String[] input = sc.nextLine().split(" ", 2);

      command = Command.parseCommand(input[0]);

      switch (command) {
        case VIEW_LIST:
          printList(tasks);
          // StringBuilder sb = new StringBuilder();
          // for (int i = 0; i < tasks.size(); i++) {
          // if (taskStatus.get(i)) {
          // checkBox = tick;
          // } else {
          // checkBox = untick;
          // }
          // sb.append(checkBox + " " + (i + 1) + ". " + tasks.get(i) + "\n" +
          // subIndentation);
          // }

          // printOutput("Here are the tasks in your list:", sb.toString());
          break;

        case BYE:
          printOutput("Goodbye my friend. See you soon!");
          isChatting = false;
          break;

        case UPDATE_MARK:
          tasks.get(Integer.parseInt(input[1]) - 1).setStatus(true);
          // int target = Integer.parseInt(input[1]) - 1;
          // taskStatus.set(target, true);
          // checkBox = tick;
          printOutput("Nice! I've marked this task as done:", tasks.get(Integer.parseInt(input[1]) - 1).toString());
          break;
        case UPDATE_UNMARK:
          tasks.get(Integer.parseInt(input[1]) - 1).setStatus(false);
          // target = Integer.parseInt(input[1]) - 1;
          // taskStatus.set(target, false);
          // checkBox = untick;
          printOutput("OK, I've marked this task as not done yet: ",
              tasks.get(Integer.parseInt(input[1]) - 1).toString());
          break;
        case INSERT_TODO:
          ToDo todoTask = new ToDo(input[1]);
          printOutput("Got it. I've added this task:", indentation + todoTask.toString(),
              "Now you have " + (tasks.size() + 1) + " tasks in the list.");
          tasks.add(todoTask);
          break;
        case INSERT_DEADLINE:
          String[] deadlineDetails = input[1].split("/by");
          Deadline deadlineTask = new Deadline(deadlineDetails[0].trim(), deadlineDetails[1].trim());
          printOutput("Got it. I've added this task:", indentation + deadlineTask.toString(),
              "Now you have " + (tasks.size() + 1) + " tasks in the list.");
          tasks.add(deadlineTask);

          break;
        case INSERT_EVENT:
          String[] eventDetails = input[1].split("/from|/to");
          Event eventTask = new Event(eventDetails[0].trim(), eventDetails[1].trim(), eventDetails[2]);
          printOutput("Got it. I've added this task:", indentation + eventTask.toString(),
              "Now you have " + (tasks.size() + 1) + " tasks in the list.");
          tasks.add(eventTask);
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
}
