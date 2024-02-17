package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.FileStorage;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;
import duke.utils.DukeException;

import java.io.IOException;

class DukeConfig {
    private final String filePath;

    public DukeConfig(String filePath) {
        this.filePath = filePath;
    }

    public static DukeConfig Default() {
        return new DukeConfig("./data/duke.txt");
    }

    public String getFilePath() {
        return filePath;
    }
}

public class Duke {
    private final TextUi ui;
    private final Storage storage;
    private TaskList tasks;

    Duke() throws IOException {
        this(DukeConfig.Default());
    }

    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

    Duke(DukeConfig config) throws IOException {
        ui = new TextUi();
        storage = new FileStorage(config.getFilePath());
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showFileLoadingError();
            tasks = new TaskList();
        }
    }

    void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Duke().run();
    }
}
