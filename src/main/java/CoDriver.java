import command.Command;
import data.exception.CoDriverException;
import parser.Parser;
import storage.Storage;
import data.Deadline;
import data.Event;
import data.TaskList;
import data.Todo;
import ui.Ui;

public class CoDriver {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public CoDriver(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showFileLoadingError();
            this.tasks = new TaskList();
        }
    }

    public void run() {
        ui.showGreeting();
        while (true) {
            String commandLine = ui.readCommand();
            ui.showLine();
            try {
                Command c = Parser.parse(commandLine);
                c.execute(tasks, ui, storage);
                if (c.isExit()) {
                    break;
                }
            } catch (CoDriverException e) {
                ui.showError(e);
            } catch (NumberFormatException e) {
                ui.showNumberFormatError();
            } finally {
                ui.showLine();
            }
        }
        ui.close();
        storage.save(this.tasks.toSaveString());
    }

    public static void main(String[] args) {
        new CoDriver("./data/codriver.txt").run();
    }
}
