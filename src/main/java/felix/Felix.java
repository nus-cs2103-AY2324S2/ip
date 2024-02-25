package felix;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import felix.command.Command;
import felix.exception.FelixException;
import felix.utils.Parser;
import felix.utils.Storage;
import felix.utils.TaskList;
import felix.utils.Ui;

/**
 * Main class
 */
public class Felix {
    private static final ArrayList<String> FILE_PATH = new ArrayList<>(List.of("data", "taskList.txt"));
    private final Ui ui;
    private final Storage storage;
    private final Parser parser;
    private TaskList tasks;

    public Felix() throws IOException {
        this.tasks = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage(FILE_PATH);
        this.parser = new Parser();
        try {
            this.tasks = this.storage.getTasksFromFile();
        } catch (FelixException err) {
            this.tasks = new TaskList();
            System.out.println(this.ui.getLoadingError(err));
        }
    }

    /**
     * Runs the application.
     */
    public void run() {
        boolean isExit = false;
        System.out.println(ui.getLogo());
        System.out.println(ui.getIntroduction());
        while (!isExit) {
            String line = ui.getNextLine();
            System.out.println(ui.getHorizontalLine());
            try {
                Command command = parser.getCommand(line);
                System.out.println(command.execute(tasks, ui, storage));
                isExit = command.isExit();
            } catch (FelixException err) {
                System.out.println(ui.getExceptionMessage(err));
            }
            System.out.println(ui.getHorizontalLine());
        }
    }

    public static void main(String[] args) throws IOException, FelixException {
        Felix felix = new Felix();
        felix.run();
    }

    public String getResponse(String input) {
        try {
            tasks = storage.getTasksFromFile();
            Command command = parser.getCommand(input);
            return command.execute(tasks, ui, storage);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
