package duke;

import java.util.Scanner;

public class Ui {

  private static final String MESSAGE_DELINEATOR =
    "______________________________________________________";
  private static final String GREET_FORMAT =
    "Hello! I'm %s! What can I do for you?";
  private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";
  private static final String MARK_MESSAGE =
    "Nice! I've marked this task as done:";
  private static final String UNMARK_MESSAGE =
    "OK, I've marked this task as not done yet:";
  private static final String LIST_MESSAGE = "Here are the tasks in your list:";
  private static final String TODO_MESSAGE = "Got it. I've added this task:";
  private static final String DELETE_MESSAGE = "Noted. I've removed this task:";
  private static final String TASKS_SUMMARY_MESSAGE =
    "Now you have %s tasks in the list.";

  private static final String NAME = ">uwu<";
  private Scanner scanner;

  public Ui() {
    scanner = new Scanner(System.in);
  }

  static void inputPrompt() {
    System.out.print(">> ");
  }

  static void greet() {
    reply(String.format(GREET_FORMAT, NAME));
  }

  static void error(String msg) {
    reply(String.format("OOPS!! %s", msg));
  }

  String getInput() {
    return scanner.nextLine();
  }

  boolean handleCommand(TaskList tasks, String command, String[] arguments)
    throws DukeException {
    messageStart();
    switch (command) {
      case "end":
        bye();
        return false;
      case "list":
        reply(LIST_MESSAGE);
        for (int idx = 0; idx < tasks.numberOfTask(); idx++) {
          reply(String.format("  %d.%s", idx + 1, tasks.peekTask(idx)));
        }
        return true;
      case "mark":
      case "unmark":
        {
          boolean isMark = command.equals("mark");
          String ferr2 = "%s command: no such task numbered %s.";
          String idxString = arguments[0];
          int idx = Integer.parseInt(idxString) - 1;
          if (!tasks.checkTaskIdx(idx)) throw new DukeException(
            String.format(ferr2, command, idxString)
          );
          tasks.setDone(idx, isMark);
          reply(isMark ? MARK_MESSAGE : UNMARK_MESSAGE);
          reply(String.format("  %s", tasks.peekTask(idx)));
          return true;
        }
      case "todo":
        {
          String taskStr = arguments[0];
          Task task = TaskList.createTodo(taskStr, false);
          tasks.addTask(task);
          reply(TODO_MESSAGE);
          reply(String.format("  %s", task));
          reply(String.format(TASKS_SUMMARY_MESSAGE, tasks.numberOfTask()));
          return true;
        }
      case "deadline":
        {
          String taskStr = arguments[0];
          String deadline = arguments[1];
          Task task = TaskList.createDeadline(taskStr, deadline, false);
          tasks.addTask(task);
          reply(TODO_MESSAGE);
          reply(String.format("  %s", task));
          reply(String.format(TASKS_SUMMARY_MESSAGE, tasks.numberOfTask()));
          return true;
        }
      case "event":
        {
          String taskStr = arguments[0];
          String fromStr = arguments[1];
          String toStr = arguments[2];
          Task task = TaskList.createEvent(taskStr, fromStr, toStr, false);
          tasks.addTask(task);
          reply(TODO_MESSAGE);
          reply(String.format("  %s", task));
          reply(String.format(TASKS_SUMMARY_MESSAGE, tasks.numberOfTask()));
          return true;
        }
      case "delete":
        {
          String ferr2 = "%s command: no such task numbered %s.";
          String idxString = arguments[0];
          int idx = Integer.parseInt(idxString) - 1;
          if (!tasks.checkTaskIdx(idx)) throw new DukeException(
            String.format(ferr2, command, idxString)
          );
          Task t = tasks.popTask(idx);
          reply(DELETE_MESSAGE);
          reply(String.format("  %s", t));
          reply(String.format(TASKS_SUMMARY_MESSAGE, tasks.numberOfTask()));
          return true;
        }
      default:
        throw new DukeException(
          String.format("Unhandled command: %s", command)
        );
    }
  }

  private static void reply(String s) {
    System.out.println("  " + s);
  }

  private static void messageStart() {
    reply("");
    reply(MESSAGE_DELINEATOR);
  }

  private static void bye() {
    reply(BYE_MESSAGE);
  }
}
