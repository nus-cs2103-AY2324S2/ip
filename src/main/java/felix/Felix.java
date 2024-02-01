package felix;

import felix.command.Command;
import felix.exception.FelixException;
import felix.utils.Parser;
import felix.utils.Storage;
import felix.utils.TaskList;
import felix.utils.Ui;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ArrayList;

public class Felix {
    private static final ArrayList<String> FILE_PATH = new ArrayList<>(List.of("data", "taskList.txt"));
    private final Ui ui;
    private final Storage storage;
    private final Parser parser;
    private TaskList tasks;


    Felix() throws IOException {
        this.tasks = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage(FILE_PATH);
        this.parser = new Parser();
        try {
            this.tasks = this.storage.getTasksFromFile();
        } catch (FelixException err) {
            this.tasks = new TaskList();
            this.ui.printLoadingError(err);
        }
    }

    public void run() throws FelixException {
        boolean isExit = false;
        this.ui.printLogo();
        this.ui.printIntroduction();
        while (!isExit) {
            String line = this.ui.getNextLine();
            this.ui.printHorizontalLine();
            try {
                Command command = this.parser.getCommand(line);
                command.execute(this.tasks, this.ui, this.storage);
                isExit = command.isExit();
            } catch (FelixException err) {
                this.ui.printException(err);
            }
            this.ui.printHorizontalLine();
        }
    }

    public static void main(String[] args) throws IOException, FelixException {
        Felix felix = new Felix();
        felix.run();
    }
}
