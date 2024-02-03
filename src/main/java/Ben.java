import commands.Command;
import exceptions.BenException;
import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import java.io.FileNotFoundException;

public class Ben {

  private Storage storage;
  private TaskList tasks;
  private Ui ui;

  public Ben(String filePath) {
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
    new Ben("data/tasks.txt").run();
  }
}
