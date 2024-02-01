import commands.Command;

import exceptions.BlawgException;
import tasklist.TaskList;
import storage.Storage;
import ui.Ui;

import parser.Parser;
public class Blawg {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    public Blawg(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.read());
        } catch (BlawgException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.getUserInput();
                ui.showLine();
                Command c = new Parser().parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
    public static void main(String[] args) {
        new Blawg("src/main/java/data/tasks.txt").run();
    }
}
