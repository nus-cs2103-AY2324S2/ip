package main.java.emis;
import main.java.emis.command.*;
import main.java.emis.exceptions.*;
import main.java.emis.task.*;

public class Duke {
    private Storage storage;
    private TaskList tasklist;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasklist = new TaskList(storage.load());
        } catch (EmisException e) {
            ui.showLoadingError();
            tasklist = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasklist, ui, storage);
                isExit = c.isExit();
            } catch (EmisException e) {
                ui.showError(e.getMessage());
            } finally {
                Ui.showLine();
            }
        }
        Ui.exit();
    }

    public static void main(String[] args) {
        new Duke("./data/emis.txt").run();
    }
}