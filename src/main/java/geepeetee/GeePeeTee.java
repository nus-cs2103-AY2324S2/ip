package geepeetee;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import exception.DukeException;
import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class GeePeeTee {

  private TaskList taskList;
  private Storage storage;
  private Ui ui;

  public static void main(String[] args) {
    new GeePeeTee("./data/GeePeeTee.txt").run();
  }

  public GeePeeTee(String filePath) {
    ui = new Ui();
    storage = new Storage("./data/GeePeeTee.txt");
    try {
      taskList = new TaskList(storage.loadTaskList());
    } catch (FileNotFoundException e) {
      ui.showFileNotFoundError();
    } catch (IOException e) {
      ui.showLoadingError();
    } catch (DukeException e) {
      ui.showErrorMessage(e.getMessage());
    }
  }

  public void run() {
    String input = "";
    ui.showWelcomeMessage();
    Scanner scanner = new Scanner(System.in);
    Parser parser = new Parser(taskList, storage, ui);
    while (!input.equals("bye")) {
        input = scanner.nextLine();
        System.out.println("\n--------------------------------------------------");
        if (input.equals("bye")) {
          scanner.close();
          ui.showGoodbyeMessage();
        }
        parser.parseInput(input);

      System.out.println("--------------------------------------------------\n");
    }
  }
}