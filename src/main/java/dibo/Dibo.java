package dibo;

import dibo.command.Command;
import dibo.exception.DiboException;

/**
 * Class of the dibo chatbot.
 */

public class Dibo {
    private static final String FILE_PATH = "./data/dibo.txt";
    private boolean isBye;
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    /**
     * Constructs a Dibo object.
     */
    public Dibo() throws DiboException {
        this.isBye = false;
        this.storage = new Storage(FILE_PATH);
        this.ui = new Ui();
        this.tasks = new TaskList(this.storage.loadData());
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            command.run(tasks, ui, storage);
            isBye = command.isBye();
            return ui.getOutput();
        } catch (DiboException e) {
            return e.getMessage();
        }

    }

    /**
     * Returns a boolean to signal if the bye command is called.
     */
    public boolean hasEnded() {
        return isBye;
    }

}
