package duke;

public class Duke {

  private TaskList tasks;
  private Ui ui;
  private Storage storage;

  public Duke(String folderPath, String fileName) {
    ui = new Ui();
    storage = new Storage(folderPath, fileName);
    try {
      tasks = new TaskList(storage.load());
    } catch (DukeException e) {
      Ui.error(e.getMessage());
      tasks = new TaskList();
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
        storage.save(tasks.getStoredTasks());
      } catch (DukeException e) {
        Ui.inputPrompt();
      }
    } while (b);
  }
}
