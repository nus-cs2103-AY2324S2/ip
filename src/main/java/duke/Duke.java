package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(Storage.retrieveList());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            isExit = Parser.parse(fullCommand);
        }
        ui.showExit();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

}
