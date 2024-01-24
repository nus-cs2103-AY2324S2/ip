import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

class DukeContext {

  public String name;
  public Scanner scanner;
  public ArrayList<String> stored_messages;

  DukeContext(String name) {
    this.name = name;
    this.scanner = new Scanner(System.in);
    this.stored_messages = new ArrayList<>();
  }
}

public class Duke {

  private static final String MESSAGE_DELINEATOR =
    "___________________________";
  private static final String GREET_FORMAT =
    "Hello! I'm %s\n" + "What can I do for you?\n";
  private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";

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
    String command = ctx.scanner.nextLine();

    message_start();
    switch (command) {
      case "end":
        bye();
        return false;
      case "list":
        {
          ListIterator<String> iter = ctx.stored_messages.listIterator();
          while (iter.hasNext()) {
            System.out.printf("%d. %s\n", iter.nextIndex(), iter.next());
          }
          break;
        }
      default:
        ctx.stored_messages.add(command);
        System.out.printf("added: %s\n", command);
    }
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
