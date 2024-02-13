import duke.*;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load(), storage);
        } catch (DukeException | FileNotFoundException e) {
            tasks = new TaskList(storage);
        }
    }

    public void run() throws IOException {
        ui.showWelcome();
        Command command;
        do {
            String input = ui.readInput();
            command = new Parser().parse(input);
            command.execute(tasks, storage);
        } while (!ExitCommand.isExit(command));
        exit();
    }

    public void exit() {
        ui.showGoodbyeMessage();
        System.exit(0);
    }
    public static void main(String[] args) throws IOException {
        new Duke("data/duke.txt").run();
    }
}

