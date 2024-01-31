package duke;

import duke.command.Parser;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcomeMessage();

        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser(tasks, storage, scanner, true);

        while (parser.getRunningStatus()) {
            String input = scanner.nextLine().trim();
            parser.setCommand(input);
            try {
                parser.processCommand();
            } catch (DukeException e) {
                throw new RuntimeException(e);
            }

        }
        scanner.close();
    }

    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }
}




