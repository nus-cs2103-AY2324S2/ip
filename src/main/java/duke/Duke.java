package duke;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.tasks.Task;

public class Duke {
    public static final Path TASKS_FILE_PATH = Paths.get(".", "data", "duke.tasks");
    public static final String ARG_DELIMITER = "\u241f";
    private static final ArrayList<Task> TASKS = new ArrayList<>();

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(Path filePath, String argDelimiter) {
        this.ui = new Ui();
        this.storage = new Storage(filePath, argDelimiter);

        try {
            this.tasks = new TaskList(this.storage.loadTasks());
        } catch (DukeException | IOException e) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }
    private void run() {
        ui.showWelcome();

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();

                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);

                isExit = command.isExit();
            } catch (DukeException | IOException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke(TASKS_FILE_PATH, ARG_DELIMITER);
        duke.run();
    }
}
