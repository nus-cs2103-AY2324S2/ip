package dibo;

import java.util.ArrayList;

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
    public Dibo() {
        this.isBye = false;
        this.storage = new Storage(FILE_PATH);
        this.ui = new Ui();
        try {
            this.tasks = new TaskList(this.storage.loadData());
        } catch (DiboException e) {
            System.out.println(e.getMessage());
            this.tasks = new TaskList(new ArrayList<>());
        }
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
