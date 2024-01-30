package Duke;

import Duke.DukeException.DukeException;
import Duke.Parser.Parser;
import Duke.Storage.Storage;
import Duke.TaskList.TaskList;
import Duke.Ui.Ui;
import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        }  catch (IOException e) {
            Ui.showLoadingError();
        }
    }

    public void run() {
        Ui.showWelcome();
        boolean finished = false;

        while(!finished) {
            try{
                String[] inputs = ui.readCommand();
                parser.parse(inputs);
                finished = parser.isFinished();
            } catch (DukeException e) {
                Ui.showError(e.toString());
            }
        }
        Ui.showEnd();
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
