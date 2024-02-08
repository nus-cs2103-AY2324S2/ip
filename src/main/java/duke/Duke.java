package duke;

import duke.command.Command;
import duke.task.TaskList;

import java.nio.file.Paths;

public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private static final String FILE_NAME = "duke.txt";
    private static final String FILE_PATH = Paths.get(".", FILE_NAME).toString();

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
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
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
    }
}
