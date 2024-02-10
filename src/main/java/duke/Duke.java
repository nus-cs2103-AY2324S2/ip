package duke;

import duke.commands.Command;
import duke.parsers.Parser;
import duke.storage.Storage;
import duke.tasks.*;
import duke.ui.Ui;

import static duke.constants.Constant.RELATIVE_PATH;


public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.getUserCommand();
            Command c = Parser.parse(fullCommand);
            c.execute(ui, tasks);
            isExit = c.isExit();
        }
    }
    public static void main(String[] args) {
        new Duke(RELATIVE_PATH).run();
    }
}
