import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class DukeException extends Exception {

  public DukeException(String message) {
    super(message);
  }
}

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
  protected static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern(
    "d/M/yyyy HH:mm"
  );
  protected static final DateTimeFormatter DATETIME_PRINT_FORMAT = DateTimeFormatter.ofPattern(
    "MMM dd yyyy HH:mm"
  );

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

  private LocalDateTime deadline;

  Deadline(String task, String deadline, boolean done) {
    super(task, done, TaskID.DEADLINE_ID);
    this.deadline = LocalDateTime.parse(deadline, DATETIME_FORMAT);
  }

  public String serialise() {
    return String.format(
      "%s%s",
      super.serialise(),
      deadline.format(DATETIME_FORMAT)
    );
  }

  public String taskStr() {
    return String.format(
      "%s (by: %s)",
      task,
      deadline.format(DATETIME_PRINT_FORMAT)
    );
  }
}

class Event extends Task {

  private LocalDateTime to;
  private LocalDateTime from;

  Event(String task, String from, String to, boolean done) {
    super(task, done, TaskID.EVENT_ID);
    this.from = LocalDateTime.parse(from, DATETIME_FORMAT);
    this.to = LocalDateTime.parse(to, DATETIME_FORMAT);
  }

  public String serialise() {
    return String.format(
      "%s%s<0>%s",
      super.serialise(),
      to.format(DATETIME_FORMAT),
      from.format(DATETIME_FORMAT)
    );
  }

  public String taskStr() {
    return String.format(
      "%s (from: %s to: %s)",
      task,
      from.format(DATETIME_PRINT_FORMAT),
      to.format(DATETIME_PRINT_FORMAT)
    );
  }
}

class Storage {

  private String filename;
  private String folderpath;

  Storage(String folderpath, String filename) {
    this.filename = filename;
    this.folderpath = folderpath;
  }

  File load() throws DukeException {
    try {
      Path baseFolder = Paths.get(System.getProperty("user.dir"));
      Path dataFolder = baseFolder.resolve(folderpath);
      if (!Files.exists(dataFolder) || !Files.isDirectory(dataFolder)) {
        dataFolder.toFile().mkdirs();
      }
      Path taskFile = dataFolder.resolve(filename);
      if (!Files.exists(taskFile)) {
        taskFile.toFile().createNewFile();
      }
      return taskFile.toFile();
    } catch (IOException e) {
      throw new DukeException("Storage.load: %s" + e);
    }
  }
}

class TaskList {

  private ArrayList<Task> storedTasks;
  private File storageFile;

  TaskList(File storageFile) throws DukeException {
    this.storageFile = storageFile;
    this.storedTasks = loadTasks();
  }

  TaskList(File storageFile, ArrayList<Task> storedTasks) {
    this.storageFile = storageFile;
    this.storedTasks = storedTasks;
  }

  private void saveTasks() throws DukeException {
    try {
      String serialised = storedTasks
        .stream()
        .<String>map(t -> t.serialise())
        .collect(Collectors.joining("<2>"));

      FileWriter f = new FileWriter(storageFile);
      f.write(serialised);
      f.close();
    } catch (IOException e) {
      throw new DukeException("DukeContext.saveTasks: " + e.getMessage());
    }
  }

  private ArrayList<Task> loadTasks() throws DukeException {
    try {
      FileInputStream f = new FileInputStream(storageFile);
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
          String taskDesc = taskData[1];
          boolean isDone = taskData[2].equals("X");
          switch (taskData[0]) {
            case "T":
              return new Todo(taskDesc, isDone);
            case "E":
              return new Event(taskDesc, auxData[0], auxData[1], isDone);
            default:
              return new Deadline(taskDesc, auxData[0], isDone);
          }
        })
        .collect(Collectors.toCollection(ArrayList::new));
    } catch (IOException e) {
      throw new DukeException("DukeContext.loadTasks: " + e.getMessage());
    }
  }

  public boolean checkTaskIdx(int idx) {
    return 0 <= idx && idx < storedTasks.size();
  }

  public int numberOfTask() {
    return storedTasks.size();
  }

  public void addTask(Task t) throws DukeException {
    storedTasks.add(t);
    saveTasks();
  }

  public void setDone(int idx, boolean done) throws DukeException {
    storedTasks.get(idx).setDone(done);
    saveTasks();
  }

  public Task popTask(int idx) throws DukeException {
    Task t = storedTasks.get(idx);
    storedTasks.remove(idx);
    saveTasks();
    return t;
  }

  public Task peekTask(int idx) {
    return storedTasks.get(idx);
  }
}

class Ui {

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
          Task task = new Todo(taskStr, false);
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
          Task task = new Deadline(taskStr, deadline, false);
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
          Task task = new Event(taskStr, fromStr, toStr, false);
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

class Parser {

  private static final String BY_CMD = "/by";
  private static final String FROM_CMD = "/from";
  private static final String TO_CMD = "/to";

  private static String cmdJoin(String[] xs) {
    return String.join(" ", xs);
  }

  private static boolean isNumber(String str) {
    return str.matches("-?\\d+(\\.\\d+)?");
  }

  static <T> T[] range(T[] xs, int a, int b) {
    return Arrays.copyOfRange(xs, a, b);
  }

  static String[] parseCommand(String cmdString) throws DukeException {
    String[] cmdSplit = cmdString.split(" ");
    String command = cmdSplit[0];
    switch (command) {
      case "end":
      case "list":
        return new String[] { command };
      case "mark":
      case "unmark":
        {
          String ferr1 = "%s command: expected an integer argument.";
          if (cmdSplit.length != 2) throw new DukeException(
            String.format(ferr1, command)
          );

          String idxString = cmdSplit[1];
          return new String[] { command, idxString };
        }
      case "todo":
        {
          if (cmdSplit.length < 2) throw new DukeException(
            "todo command: description cannot be empty."
          );
          String taskStr = cmdJoin(range(cmdSplit, 1, cmdSplit.length));
          return new String[] { command, taskStr };
        }
      case "deadline":
        {
          List<String> cmds = Arrays.asList(cmdSplit);
          String ferr1 = "deadline command: expected `%s` argument.";
          String ferr2 = "deadline command: %s description cannot be empty.";
          if (!cmds.contains(BY_CMD)) throw new DukeException(
            String.format(ferr1, BY_CMD)
          );
          int by_idx = cmds.indexOf(BY_CMD);
          String taskStr = cmdJoin(range(cmdSplit, 1, by_idx));
          String deadline = cmdJoin(range(cmdSplit, by_idx + 1, cmds.size()));
          if (taskStr.length() == 0) throw new DukeException(
            String.format(ferr2, "task")
          );
          if (deadline.length() == 0) throw new DukeException(
            String.format(ferr2, "deadline")
          );
          return new String[] { command, taskStr, deadline };
        }
      case "event":
        {
          List<String> cmds = Arrays.asList(cmdSplit);
          String ferr1 = "event command: expected `%s` argument.";
          String ferr2 = "event command: %s description cannot be empty.";
          String ferr3 =
            "event command: `%s` argument expected before `%s` argument.";
          if (!cmds.contains(FROM_CMD)) throw new DukeException(
            String.format(ferr1, FROM_CMD)
          );
          if (!cmds.contains(TO_CMD)) throw new DukeException(
            String.format(ferr1, TO_CMD)
          );
          int fromIdx = cmds.indexOf(FROM_CMD);
          int toIdx = cmds.indexOf(TO_CMD);
          if (toIdx < fromIdx) throw new DukeException(
            String.format(ferr3, FROM_CMD, TO_CMD)
          );
          String taskStr = cmdJoin(range(cmdSplit, 1, fromIdx));
          String fromStr = cmdJoin(range(cmdSplit, fromIdx + 1, toIdx));
          String toStr = cmdJoin(range(cmdSplit, toIdx + 1, cmds.size()));
          if (taskStr.length() == 0) throw new DukeException(
            String.format(ferr2, "task")
          );
          if (fromStr.length() == 0) throw new DukeException(
            String.format(ferr2, "from")
          );
          if (toStr.length() == 0) throw new DukeException(
            String.format(ferr2, "to")
          );
          return new String[] { command, taskStr, fromStr, toStr };
        }
      case "delete":
        {
          String ferr1 = "%s command: expected an integer argument.";
          if (cmdSplit.length != 2) throw new DukeException(
            String.format(ferr1, command)
          );
          String idxString = cmdSplit[1];
          if (!isNumber(idxString)) throw new DukeException(
            String.format(ferr1, command)
          );
          return new String[] { command, idxString };
        }
      default:
        throw new DukeException(
          String.format("Unhandled command: %s", command)
        );
    }
  }
}

public class Duke {

  private TaskList tasks;
  private Ui ui;

  public Duke(String folderPath, String fileName) throws DukeException {
    ui = new Ui();
    Storage storage = new Storage(folderPath, fileName);
    File storageFile = storage.load();

    try {
      tasks = new TaskList(storageFile);
    } catch (DukeException e) {
      Ui.error(e.getMessage());
      tasks = new TaskList(storageFile, new ArrayList<>());
    }
  }

  public void run() {
    Ui.greet();
    boolean b = true;
    do {
      Ui.inputPrompt();
      try {
        String[] cmd = Parser.parseCommand(ui.getInput());
        String command = cmd[0];
        String[] arguments = Parser.range(cmd, 1, cmd.length);
        b = ui.handleCommand(tasks, command, arguments);
      } catch (DukeException e) {
        Ui.inputPrompt();
      }
    } while (b);
  }

  public static void main(String[] args) {
    try {
      new Duke("data", "tasks.txt").run();
    } catch (DukeException e) {
      Ui.error(e.getMessage());
    }
  }
}
