package duke;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {
    private boolean isRunning;
    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private Parser parser;

    public static void main(String[] args) {
        Duke duke = new Duke("./data/tasklist.txt");
        duke.run();
    }
    
    public Duke(String filepath) {
        ui = new Ui();
        parser = new Parser();
        
        try {
            this.storage = new Storage(filepath);
            this.taskList = new TaskList(this.storage.loadStorage());
        } catch (DukeException e) {
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
            } catch (DukeException e) {
                ui.printMessage(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
        
        try {
            storage.saveToStorage(taskList.getTaskList());
        } catch (DukeException e) {
            ui.printMessage(e.getMessage());
        }
        
    }
}
