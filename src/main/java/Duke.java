import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

class DukeContext {

  public String name;
  public Scanner scanner;
  public ArrayList<String> stored_tasks;
  public ArrayList<Boolean> tasks_done;

  DukeContext(String name) {
    this.name = name;
    this.scanner = new Scanner(System.in);
    this.stored_tasks = new ArrayList<>();
    this.tasks_done = new ArrayList<>();
  }

  public void add_task(String task) {
    this.stored_tasks.add(task);
    this.tasks_done.add(false);
  }

  public void print_task(int idx) {
    System.out.printf(
      "  %d.[%s] %s\n",
      idx + 1,
      this.tasks_done.get(idx) ? "X" : " ",
      this.stored_tasks.get(idx)
    );
  }

  public void print_tasks() {
    for (int idx = 0; idx < this.stored_tasks.size(); idx++) {
      this.print_task(idx);
    }
  }

  public boolean mark_task(int idx, boolean done) {
    if (0 > idx || idx >= this.stored_tasks.size()) return false;
    this.tasks_done.set(idx, done);
    return true;
  }
}

public class Duke {

  private static final String MESSAGE_DELINEATOR =
    "___________________________";
  private static final String GREET_FORMAT =
    "Hello! I'm %s\n" + "What can I do for you?\n";
  private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";
  private static final String MARK_MESSAGE =
    "Nice! I've marked this task as done:";
  private static final String UNMARK_MESSAGE =
    "OK, I've marked this task as not done yet:";

  public static boolean is_number(String str) {
    return str.matches("-?\\d+(\\.\\d+)?");
  }

  private static void input_prompt() {
    System.out.printf(">> ");
  }

  private static void message_start() {
    System.out.println(MESSAGE_DELINEATOR);
  }

  private static void greet(DukeContext ctx) {
    System.out.printf(GREET_FORMAT, ctx.name);
  }

  private static void bye() {
    System.out.println(BYE_MESSAGE);
  }

  private static boolean handle_command(DukeContext ctx) {
    String input = ctx.scanner.nextLine();
    String[] commands = input.split(" ");

    message_start();
    switch (commands[0]) {
      case "end":
        bye();
        return false;
      case "list":
        ctx.print_tasks();
        return true;
      case "mark":
      case "unmark":
        boolean is_mark = commands[0].equals("mark");
        if (commands.length != 2) break;
        String idx_s = commands[1];
        if (!is_number(idx_s)) break;
        int idx = Integer.parseInt(idx_s) - 1;
        if (!ctx.mark_task(idx, is_mark)) break;
        System.out.println(is_mark ? MARK_MESSAGE : UNMARK_MESSAGE);
        ctx.print_task(idx);
        return true;
      default:
        break;
    }
    ctx.add_task(input);
    System.out.printf("added: %s\n", input);
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
