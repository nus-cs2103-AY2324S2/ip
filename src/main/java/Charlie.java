import Charlie.commands.Command;
import Charlie.exceptions.CharlieException;
import Charlie.parser.Parser;
import Charlie.storage.Storage;
import Charlie.storage.TaskList;
import Charlie.ui.Ui;

public class Charlie {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Charlie(String filePath) {
        try {
            ui = new Ui();
            storage = new Storage(filePath);
            tasks = new TaskList(storage.loadTasks());
        } catch (CharlieException exception) {
            ui.showLoadingError(exception.getMessage());
            tasks = new TaskList();
        }
    }


    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (CharlieException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Charlie("./data/charlie.txt").run();
    }


}
