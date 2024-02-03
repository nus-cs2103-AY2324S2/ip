package toothless;

import toothless.exception.ToothlessException;
import toothless.parser.Parser;
import toothless.storage.Storage;
import toothless.task.TaskList;
import toothless.ui.Ui;

public class Toothless {
    private boolean isRunning;
    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private Parser parser;

    public static void main(String[] args) {
        Toothless toothless = new Toothless("./data/tasklist.txt");
        toothless.run();
    }
    
    public Toothless(String filepath) {
        ui = new Ui();
        parser = new Parser();
        
        try {
            this.storage = new Storage(filepath);
            this.taskList = new TaskList(this.storage.loadStorage());
        } catch (ToothlessException e) {
            ui.printMessage(e.getMessage());
            ui.printMessage("Sorry, tasklist.txt is corrupted. Starting a blank tasklist.");
            this.taskList = new TaskList();
        }
    }

    public void run() {
        this.isRunning = true;
        ui.printWelcome();
        
        while (this.isRunning) {
            try {
                String userInput = ui.readCommand();
                ui.printLine();
                this.isRunning = parser.parseInput(userInput, taskList, ui);
            } catch (ToothlessException e) {
                ui.printMessage(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
        
        try {
            storage.saveToStorage(taskList.getTaskList());
        } catch (ToothlessException e) {
            ui.printMessage(e.getMessage());
        }
        
    }
}
