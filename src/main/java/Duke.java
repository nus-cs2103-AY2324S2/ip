import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

enum TaskID {
  TODO_ID("T"),
  EVENT_ID("E"),
  DEADLINE_ID("D");

  private String id;

  TaskID(String id) {
    this.id = id;
  }

  public String toString() {
    return id;
  }
}

abstract class Task {

  protected boolean done;
  protected TaskID id;
  protected String task;

  Task(String task, boolean done, TaskID id) {
    this.done = done;
    this.id = id;
    this.task = task;
  }

  public boolean isDone() {
    return done;
  }

  public void setDone(boolean done) {
    this.done = done;
  }

  public String taskId() {
    return id.toString();
  }

  public String toString() {
    return String.format("[%s][%s] %s", id, done ? "X" : " ", taskStr());
  }

  public String serialise() {
    return String.format("%s<0>%s<0>%s<1>", id, task, done ? "X" : " ");
  }

  public abstract String taskStr();
}

class Todo extends Task {

  Todo(String task, boolean done) {
    super(task, done, TaskID.TODO_ID);
  }

  public String serialise() {
    return String.format("%s", super.serialise());
  }

  public String taskStr() {
    return task;
  }
}

class Deadline extends Task {

  private String deadline;

  Deadline(String task, String deadline, boolean done) {
    super(task, done, TaskID.DEADLINE_ID);
    this.deadline = deadline;
  }

  public String serialise() {
    return String.format("%s%s", super.serialise(), deadline);
  }

  public String taskStr() {
    return String.format("%s (by: %s)", task, deadline);
  }
}

class Event extends Task {

  private String to;
  private String from;

  Event(String task, String from, String to, boolean done) {
    super(task, done, TaskID.EVENT_ID);
    this.from = from;
    this.to = to;
  }

  public String serialise() {
    return String.format("%s%s<0>%s", super.serialise(), to, from);
  }

  public String taskStr() {
    return String.format("%s (from: %s to: %s)", task, from, to);
  }
}

class DukeContext {

  private String name;
  private Scanner scanner;
  private ArrayList<Task> storedTasks;

  DukeContext(String name) {
    this.name = name;
    this.scanner = new Scanner(System.in);
    this.storedTasks = loadTasks();
  }

  private File getFileHandle() throws IOException {
    Path baseFolder = Paths.get(System.getProperty("user.dir"));
    Path dataFolder = baseFolder.resolve("data");
    if (!Files.exists(dataFolder) || !Files.isDirectory(dataFolder)) {
      dataFolder.toFile().mkdirs();
    }
    Path taskFile = dataFolder.resolve("tasks.bin");
    if (!Files.exists(taskFile)) {
      taskFile.toFile().createNewFile();
    }

    return taskFile.toFile();
  }

  private void saveTasks() {
    try {
      String serialised = storedTasks
        .stream()
        .<String>map(t -> t.serialise())
        .collect(Collectors.joining("<2>"));

      FileWriter f = new FileWriter(getFileHandle());
      f.write(serialised);
      f.close();
    } catch (IOException e) {
      System.err.println("DukeContext.saveTasks: " + e.getMessage());
    }
  }

  private ArrayList<Task> loadTasks() {
    try {
      FileInputStream f = new FileInputStream(getFileHandle());
      String serialised = new String(f.readAllBytes());
      f.close();
      String[] tasksString = serialised.split("<2>");
      return Arrays
        .stream(tasksString)
        .filter(s -> s.length() > 0)
        .map(s -> {
          String[] xs = s.split("<1>");
          String[] taskData = xs[0].split("<0>");
          String[] auxData = xs.length == 1
            ? new String[0]
            : xs[1].split("<0>");
          boolean isDone = taskData[2].equals("X");
          switch (taskData[0]) {
            case "T":
              return new Todo(taskData[1], isDone);
            case "E":
              return new Event(taskData[1], auxData[0], auxData[1], isDone);
            default:
              return new Deadline(taskData[1], auxData[0], isDone);
          }
        })
        .collect(Collectors.toCollection(ArrayList::new));
    } catch (IOException e) {
      System.err.println("DukeContext.loadTasks: " + e.getMessage());
      return new ArrayList<>();
    }
  }

  public boolean checkTaskIdx(int idx) {
    return 0 <= idx && idx < storedTasks.size();
  }

  public String getName() {
    return name;
  }

  public Scanner getScanner() {
    return scanner;
  }

  public int numberOfTask() {
    return storedTasks.size();
  }

  public void addTask(Task t) {
    storedTasks.add(t);
    saveTasks();
  }

  public void setDone(int idx, boolean done) {
    storedTasks.get(idx).setDone(done);
    saveTasks();
  }

  public Task popTask(int idx) {
    Task t = storedTasks.get(idx);
    storedTasks.remove(idx);
    saveTasks();
    return t;
  }

  public Task peekTask(int idx) {
    return storedTasks.get(idx);
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
  private static final String DELETE_MESSAGE = "Noted. I've removed this task:";
  private static final String TASKS_SUMMARY_MESSAGE =
    "Now you have %s tasks in the list.";
  private static final String BY_CMD = "/by";
  private static final String FROM_CMD = "/from";
  private static final String TO_CMD = "/to";

  private static String cmdJoin(String[] xs) {
    return String.join(" ", xs);
  }

  private static <T> T[] range(T[] xs, int a, int b) {
    return Arrays.copyOfRange(xs, a, b);
  }

  private static void reply(String s) {
    System.out.println("  " + s);
  }

  private static boolean isNumber(String str) {
    return str.matches("-?\\d+(\\.\\d+)?");
  }

  private static void inputPrompt() {
    System.out.print(">> ");
  }

  private static void error(String msg) {
    reply(String.format("OOPS!! %s", msg));
  }

  private static void messageStart() {
    reply("");
    reply(MESSAGE_DELINEATOR);
  }

  private static void greet(DukeContext ctx) {
    reply(String.format(GREET_FORMAT, ctx.getName()));
  }

  private static void bye() {
    reply(BYE_MESSAGE);
  }

  private static boolean handleCommand(DukeContext ctx) {
    String input = ctx.getScanner().nextLine();
    String[] commands = input.split(" ");
    String c = commands[0];
    String errorStr;

    messageStart();
    switch (c) {
      case "end":
        bye();
        return false;
      case "list":
        reply(LIST_MESSAGE);
        for (int idx = 0; idx < ctx.numberOfTask(); idx++) {
          reply(String.format("  %d.%s", idx + 1, ctx.peekTask(idx)));
        }
        return true;
      case "mark":
      case "unmark":
        {
          boolean isMark = c.equals("mark");
          String ferr1 = "%s command: expected an integer argument.";
          String ferr2 = "%s command: no such task numbered %s.";
          if (commands.length != 2) {
            errorStr = String.format(ferr1, c);
            break;
          }
          String idxString = commands[1];
          if (!isNumber(idxString)) {
            errorStr = String.format(ferr1, c);
            break;
          }
          int idx = Integer.parseInt(idxString) - 1;
          if (!ctx.checkTaskIdx(idx)) {
            errorStr = String.format(ferr2, c, idxString);
            break;
          }
          ctx.setDone(idx, isMark);
          reply(isMark ? MARK_MESSAGE : UNMARK_MESSAGE);
          reply(String.format("  %s", ctx.peekTask(idx)));
          return true;
        }
      case "todo":
        {
          if (commands.length < 2) {
            errorStr = "todo command: description cannot be empty.";
            break;
          }
          String taskStr = cmdJoin(range(commands, 1, commands.length));
          Task task = new Todo(taskStr, false);
          ctx.addTask(task);
          reply(TODO_MESSAGE);
          reply(String.format("  %s", task));
          reply(String.format(TASKS_SUMMARY_MESSAGE, ctx.numberOfTask()));
          return true;
        }
      case "deadline":
        {
          List<String> cmds = Arrays.asList(commands);
          String ferr1 = "deadline command: expected `%s` argument.";
          String ferr2 = "deadline command: %s description cannot be empty.";
          if (!cmds.contains(BY_CMD)) {
            errorStr = String.format(ferr1, BY_CMD);
            break;
          }
          int by_idx = cmds.indexOf(BY_CMD);
          String taskStr = cmdJoin(range(commands, 1, by_idx));
          String deadline = cmdJoin(range(commands, by_idx + 1, cmds.size()));
          if (taskStr.length() == 0) {
            errorStr = String.format(ferr2, "task");
            break;
          }
          if (deadline.length() == 0) {
            errorStr = String.format(ferr2, "deadline");
            break;
          }
          Task task = new Deadline(taskStr, deadline, false);
          ctx.addTask(task);
          reply(TODO_MESSAGE);
          reply(String.format("  %s", task));
          reply(String.format(TASKS_SUMMARY_MESSAGE, ctx.numberOfTask()));
          return true;
        }
      case "event":
        {
          List<String> cmds = Arrays.asList(commands);
          String ferr1 = "event command: expected `%s` argument.";
          String ferr2 = "event command: %s description cannot be empty.";
          String ferr3 =
            "event command: `%s` argument expected before `%s` argument.";
          if (!cmds.contains(FROM_CMD)) {
            errorStr = String.format(ferr1, FROM_CMD);
            break;
          }
          if (!cmds.contains(TO_CMD)) {
            errorStr = String.format(ferr1, TO_CMD);
            break;
          }
          int fromIdx = cmds.indexOf(FROM_CMD);
          int toIdx = cmds.indexOf(TO_CMD);
          if (toIdx < fromIdx) {
            errorStr = String.format(ferr3, FROM_CMD, TO_CMD);
            break;
          }
          String taskStr = cmdJoin(range(commands, 1, fromIdx));
          String fromStr = cmdJoin(range(commands, fromIdx + 1, toIdx));
          String toStr = cmdJoin(range(commands, toIdx + 1, cmds.size()));
          if (taskStr.length() == 0) {
            errorStr = String.format(ferr2, "task");
            break;
          }
          if (fromStr.length() == 0) {
            errorStr = String.format(ferr2, "from");
            break;
          }
          if (toStr.length() == 0) {
            errorStr = String.format(ferr2, "to");
            break;
          }
          Task task = new Event(taskStr, fromStr, toStr, false);
          ctx.addTask(task);
          reply(TODO_MESSAGE);
          reply(String.format("  %s", task));
          reply(String.format(TASKS_SUMMARY_MESSAGE, ctx.numberOfTask()));
          return true;
        }
      case "delete":
        {
          String ferr1 = "%s command: expected an integer argument.";
          String ferr2 = "%s command: no such task numbered %s.";
          if (commands.length != 2) {
            errorStr = String.format(ferr1, c);
            break;
          }
          String idxString = commands[1];
          if (!isNumber(idxString)) {
            errorStr = String.format(ferr1, c);
            break;
          }
          int idx = Integer.parseInt(idxString) - 1;
          if (!ctx.checkTaskIdx(idx)) {
            errorStr = String.format(ferr2, c, idxString);
            break;
          }
          Task t = ctx.popTask(idx);
          reply(DELETE_MESSAGE);
          reply(String.format("  %s", t));
          reply(String.format(TASKS_SUMMARY_MESSAGE, ctx.numberOfTask()));
          return true;
        }
      default:
        errorStr = String.format("Unhandled command: %s", c);
        break;
    }
    error(errorStr);
    return true;
  }

  public static void main(String[] args) {
    DukeContext ctx = new DukeContext(">uwu<");

    greet(ctx);
    do {
      inputPrompt();
    } while (handleCommand(ctx));
  }
}
