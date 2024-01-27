package duke;

import java.util.Scanner;
import duke.Command.Command;
import database.TaskORM;

public class Duke {
  private static final String logo = " ____        _        \n"
    + "|  _ \\ _   _| | _____ \n"
    + "| | | | | | | |/ / _ \\\n"
    + "| |_| | |_| |   <  __/\n"
    + "|____/ \\__,_|_|\\_\\___|\n";
  private final String chatBotName;
  private final TaskORM taskManager = new TaskORM();

  public Duke (String name) {
    this.chatBotName = name;
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
      view.PrettyPrinter.print(output);
    }

    sc.close();
  }

  public void run() {
    view.PrettyPrinter.print(logo);

    view.PrettyPrinter.print(this.greetMsg());

    this.REPL();

    view.PrettyPrinter.print(this.exitMsg());
  }
}
