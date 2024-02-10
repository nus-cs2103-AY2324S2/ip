import commands.Command;
import parsers.Parser;
import storage.Storage;
import tasks.*;
import ui.Ui;

import static constants.Constant.RELATIVE_PATH;


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
