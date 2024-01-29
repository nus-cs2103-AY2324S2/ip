package johnny;

import johnny.commands.Command;
import johnny.exceptions.JohnnyException;
import johnny.parser.Parser;
import johnny.storage.Storage;
import johnny.tasks.TaskList;
import johnny.ui.Ui;

public class Johnny {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Johnny(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (JohnnyException e) {
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
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (JohnnyException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Johnny("src/main/data/johnny.commands.tasks.txt").run();
    }

}
