import command.Command;
import exception.BuddyException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class Buddy {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Buddy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (BuddyException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    private void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (BuddyException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Buddy("tasks.txt").run();
    }
}
