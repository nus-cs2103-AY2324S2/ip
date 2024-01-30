import task.TaskList;
import utilities.Ui;
import command.Command;
import exceptions.DukeException;
import utilities.Storage;
import utilities.Parser;

public class Duke {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    public Duke(String fileName, String directoryName) {
        this.ui = new Ui();
        this.storage = new Storage(fileName, directoryName);
        this.taskList = new TaskList(storage.load());
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.readCommand();
                Command command = Parser.parse(userInput);
                command.execute(taskList, storage, ui);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("duke.txt", "./src/main/data").run();
    }
}
