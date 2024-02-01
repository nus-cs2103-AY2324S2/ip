package taskList;

import java.util.Scanner;
import taskList.commands.Command;

public class Duke {
  private Storage storage;
  private TaskList taskList;
  private Ui ui;

  public Duke(String filePath) {
    ui = new Ui();
    storage = new Storage(filePath);
    taskList = storage.loadTasks();
  }

  public void run() {
    Scanner scanner = new Scanner(System.in);
    ui.showWelcomeMessage();
    do { // continue the program until 'bye' command is inputted.
      String input = scanner.nextLine().trim();
      Command c = Parser.parseCommand(input);
      c.execute(this.taskList, this.ui, this.storage);
      this.storage.saveTasks(taskList);
      System.out.println("____________________________________________________________");
    } while (ui.isRunning);

    scanner.close();
  }

  public static void main(String[] args) {
    new Duke("./data/duke.ser").run();
  }
}

// ------------------------------------------------------------------------------------------------------------------------------------------------
