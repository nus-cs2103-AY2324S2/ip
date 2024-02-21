package duchess;

import duchess.util.DuchessException;
import duchess.util.Parser;
import duchess.util.Storage;
import duchess.util.TaskList;
import duchess.util.Ui;

public class Duchess {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Create new Duchess object.
     * Create new Ui, Storage, Parser, and TaskList objects.
     */
    public Duchess() {
        ui = new Ui();
        storage = new Storage("tasks.txt");
        parser = new Parser();

        try {
            tasks = new TaskList(storage.load());
        } catch (DuchessException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }

    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getWelcome() {
        return ui.printMenu();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            return parser.getAction(input, tasks, ui, storage);
        } catch (DuchessException e) {
            e.printStackTrace(System.err);
            return e.getMessage();
        } catch (Exception e) {
            e.printStackTrace(System.err);
            return e.getMessage();
        }
    }
}