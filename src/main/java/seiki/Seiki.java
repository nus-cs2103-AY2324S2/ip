package seiki;

import seiki.commands.Command;
import seiki.data.TaskList;
import seiki.data.exception.SeikiException;
import seiki.parser.Parser;
import seiki.storage.Storage;
import seiki.ui.Ui;

public class Seiki {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Seiki(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (SeikiException e) {
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line
                Command c = Parser.parse(fullCommand);
                c.execute(storage, tasks, ui);
                isExit = c.isExit();
            } catch (SeikiException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Seiki("data/tasks.txt").run();
    }

}
