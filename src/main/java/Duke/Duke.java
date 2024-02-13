package Duke;

import java.io.*;

public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            Ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() throws DukeException {
        ui.welcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            String userInput = ui.readInput();
            String result = Parser.parse(userInput, tasks, ui, storage);
            if (result != null && result.equals("1")) {
                isExit = true;
                ui.closeScanner();
            }
        }
    }

    public static void main(String[] args) throws DukeException {
        new Duke("./data/duke.txt").run();
    }
}