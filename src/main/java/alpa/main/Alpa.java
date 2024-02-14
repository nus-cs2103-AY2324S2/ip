package alpa.main;

import alpa.utils.*;
import alpa.commands.*;
import alpa.exceptions.*;
import alpa.tasks.*;
import alpa.ui.*;

import java.util.Scanner;

public class Alpa {
  private static final String FILE_PATH = "./data/tasks.txt";
  private Storage storage;
  private TaskList tasks;
  private Ui ui;

  public Alpa() {
    ui = new Ui();
    storage = new Storage(FILE_PATH);
    try {
      tasks = new TaskList();
      tasks.addAll(storage.loadTasks());
    } catch (AlpaException e) {
      ui.showLoadingError(e.getMessage());
      tasks = new TaskList();
    }
  }

  public void runAlpa() {
    ui.showWelcome();
    boolean isExit = false;
    while (!isExit) {
      try {
        String fullCommand = ui.readCommand();
        Command c = Parser.parse(fullCommand);
        c.executeCommand(tasks, ui, storage);
        isExit = c.isExit();
      } catch (AlpaException e) {
        ui.showError(e.getMessage());
      }
    }
  }
    
  public static void main(String[] args) {
    new Alpa().runAlpa();
  }
}

