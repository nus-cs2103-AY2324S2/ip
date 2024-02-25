package brian;

import java.io.IOException;

import brian.command.Command;
import brian.parser.Parser;
import brian.storage.FileStorage;
import brian.storage.Storage;
import brian.task.TaskList;
import brian.ui.TextUi;
import brian.utils.BrianException;

class Config {
    private final String filePath;

    public Config(String filePath) {
        this.filePath = filePath;
    }

    public static Config Default() {
        return new Config("./data/duke.txt");
    }

    public String getFilePath() {
        return filePath;
    }
}

public class Brian {
    private final TextUi ui;
    private final Storage storage;
    private TaskList tasks;

    Brian() throws IOException {
        this(Config.Default());
    }

    Brian(Config config) throws IOException {
        ui = new TextUi();
        storage = new FileStorage(config.getFilePath());
        try {
            tasks = new TaskList(storage.load());
        } catch (BrianException e) {
            ui.showFileLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) throws IOException {
        new Brian().run();
    }

    public String getResponse(String input) {
        return "Brian heard: " + input;
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
            } catch (BrianException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
}
