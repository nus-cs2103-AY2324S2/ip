package ben;

import ben.commands.Command;
import ben.exceptions.BenException;
import ben.parser.Parser;
import ben.storage.Storage;
import ben.tasks.TaskList;
import ben.ui.Ui;

import java.io.FileNotFoundException;

public class Main {

  private Storage storage;
  private TaskList tasks;
  private Ui ui;

  public Main(String filePath) {
    ui = new Ui();
    storage = new Storage(filePath);
    try {
      tasks = new TaskList(storage.load());
    } catch (BenException | FileNotFoundException e) {
      ui.showError(e.getMessage());
      tasks = new TaskList();
    }
  }

  public void run() {
    ui.showWelcome();
    boolean isExit = false;
    while (!isExit) {
      try {
        String fullCommand = ui.readCommand();
        ui.showLine(); // show the divider line ("_______")
        Command c = Parser.parse(fullCommand);
        c.execute(tasks, ui, storage);
        isExit = c.isExit();
        storage.save(tasks.formatSave());
      } catch (BenException e) {
        ui.showError(e.getMessage());
      } finally {
        ui.showLine();
      }
    }
  }


  public static void main(String[] args) {
    new Main("data/tasks.txt").run();
  }
}
