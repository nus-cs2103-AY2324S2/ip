package duke;

import java.util.Scanner;

import database.DB;
import duke.command.Command;
import database.TaskORM;

/**
 * The Duke class is the main class of the application.
 * It is responsible for the REPL (Read-Eval-Print-Loop).
 */
public class Duke {
  private static final String logo = " ____        _        \n"
    + "|  _ \\ _   _| | _____ \n"
    + "| | | | | | | |/ / _ \\\n"
    + "| |_| | |_| |   <  __/\n"
    + "|____/ \\__,_|_|\\_\\___|\n";
  private final String chatBotName;
  private final TaskORM taskManager;
  private final view.PrettyPrinter printer;

  /**
   * Constructor for Duke.
   * @param name The name of the chatbot.
   */
  public Duke (String name) {
    this.chatBotName = name;
    this.taskManager = new TaskORM();
    this.printer = new view.PrettyPrinter();
  }

  private String greetMsg() {
    return "Hello! I'm " + this.chatBotName + "\n"
      + "What can I do for you?\n";
  }

  private String exitMsg() {
    return "Bye. Hope to see you again soon!\n";
  }

  private void REPL() {
    Scanner sc = new Scanner(System.in);

    while (true) {
      String input = sc.nextLine();

      Command c = Command.Interpret(input);

      if (c.terminate()) break;

      String output = c.execute(this.taskManager);
      printer.print(output);
    }

    sc.close();
  }

  /**
   * Runs the Duke application.
   * Greets the user, then enters the REPL.
   * Finally, says goodbye to the user on exit.
   */
  public void run() {
    printer.print(logo);

    printer.print(this.greetMsg());

    this.REPL();

    printer.print(this.exitMsg());
  }
}
