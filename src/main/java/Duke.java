import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

abstract class Task {

  protected boolean done;
  protected String id;
  protected String task;

  Task(String task, boolean done, String id) {
    this.done = done;
    this.id = id;
    this.task = task;
  }

  public boolean is_done() {
    return done;
  }

  public void set_done(boolean done) {
    this.done = done;
  }

  public String task_id() {
    return id;
  }

  public String toString() {
    return String.format("[%s][%s] %s", id, done ? "X" : " ", task_str());
  }

  public abstract String task_str();
}

class Todo extends Task {

  Todo(String task) {
    super(task, false, "T");
  }

  public String task_str() {
    return task;
  }
}

class Deadline extends Task {

  private String deadline;

  Deadline(String task, String deadline) {
    super(task, false, "D");
    this.deadline = deadline;
  }

  public String task_str() {
    return String.format("%s (by: %s)", task, deadline);
  }
}

class Event extends Task {

  private String to;
  private String from;

  Event(String task, String from, String to) {
    super(task, false, "E");
    this.from = from;
    this.to = to;
  }

  public String task_str() {
    return String.format("%s (from: %s to: %s)", task, from, to);
  }
}

class DukeContext {

  public String name;
  public Scanner scanner;
  public ArrayList<Task> stored_tasks;

  DukeContext(String name) {
    this.name = name;
    this.scanner = new Scanner(System.in);
    this.stored_tasks = new ArrayList<>();
  }

  public boolean check_taskidx(int idx) {
    return 0 <= idx && idx < stored_tasks.size();
  }
}

public class Duke {

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
  private static final String TASKS_SUMMARY_MESSAGE =
    "Now you have %s tasks in the list.";
  private static final String BY_CMD = "/by";
  private static final String FROM_CMD = "/from";
  private static final String TO_CMD = "/to";

  private static String cmd_join(String[] xs) {
    return String.join(" ", xs);
  }

  private static <T> T[] range(T[] xs, int a, int b) {
    return Arrays.copyOfRange(xs, a, b);
  }

  private static void reply(String s) {
    System.out.println("  " + s);
  }

  public static boolean is_number(String str) {
    return str.matches("-?\\d+(\\.\\d+)?");
  }

  private static void input_prompt() {
    System.out.print(">> ");
  }

  private static void error(String msg) {
    reply(String.format("ERROR: %s", msg));
  }

  private static void message_start() {
    reply("");
    reply(MESSAGE_DELINEATOR);
  }

  private static void greet(DukeContext ctx) {
    reply(String.format(GREET_FORMAT, ctx.name));
  }

  private static void bye() {
    reply(BYE_MESSAGE);
  }

  private static boolean handle_command(DukeContext ctx) {
    String input = ctx.scanner.nextLine();
    String[] commands = input.split(" ");
    String c = commands[0];

    message_start();
    switch (c) {
      case "end":
        bye();
        return false;
      case "list":
        reply(LIST_MESSAGE);
        for (int idx = 0; idx < ctx.stored_tasks.size(); idx++) {
          reply(String.format("  %d.%s", idx + 1, ctx.stored_tasks.get(idx)));
        }
        return true;
      case "mark":
      case "unmark":
        boolean is_mark = c.equals("mark");
        if (commands.length != 2) break;
        String idx_s = commands[1];
        if (!is_number(idx_s)) break;
        int idx = Integer.parseInt(idx_s) - 1;
        if (!ctx.check_taskidx(idx)) break;
        ctx.stored_tasks.get(idx).set_done(is_mark);
        reply(is_mark ? MARK_MESSAGE : UNMARK_MESSAGE);
        reply(String.format("  %s", ctx.stored_tasks.get(idx)));
        return true;
      case "todo":
        {
          String task_str = cmd_join(range(commands, 1, commands.length));
          Task task = new Todo(task_str);
          ctx.stored_tasks.add(task);
          reply(TODO_MESSAGE);
          reply(String.format("  %s", task));
          reply(String.format(TASKS_SUMMARY_MESSAGE, ctx.stored_tasks.size()));
          return true;
        }
      case "deadline":
        {
          List<String> cmds = Arrays.asList(commands);
          if (!cmds.contains(BY_CMD)) break;
          int by_idx = cmds.indexOf(BY_CMD);
          String task_str = cmd_join(range(commands, 1, by_idx));
          String deadline = cmd_join(range(commands, by_idx + 1, cmds.size()));
          Task task = new Deadline(task_str, deadline);
          ctx.stored_tasks.add(task);
          reply(TODO_MESSAGE);
          reply(String.format("  %s", task));
          reply(String.format(TASKS_SUMMARY_MESSAGE, ctx.stored_tasks.size()));
          return true;
        }
      case "event":
        {
          List<String> cmds = Arrays.asList(commands);
          if (!cmds.contains(FROM_CMD)) break;
          if (!cmds.contains(TO_CMD)) break;
          int from_idx = cmds.indexOf(FROM_CMD);
          int to_idx = cmds.indexOf(TO_CMD);
          if (to_idx < from_idx) break;
          String task_str = cmd_join(range(commands, 1, from_idx));
          String from_str = cmd_join(range(commands, from_idx + 1, to_idx));
          String to_str = cmd_join(range(commands, to_idx + 1, cmds.size()));
          Task task = new Event(task_str, from_str, to_str);
          ctx.stored_tasks.add(task);
          reply(TODO_MESSAGE);
          reply(String.format("  %s", task));
          reply(String.format(TASKS_SUMMARY_MESSAGE, ctx.stored_tasks.size()));
          return true;
        }
      default:
        break;
    }
    error(String.format("Error processing command: %s", input));
    return true;
  }

  public static void main(String[] args) {
    DukeContext ctx = new DukeContext(">uwu<");

    greet(ctx);
    do {
      input_prompt();
    } while (handle_command(ctx));
  }
}
