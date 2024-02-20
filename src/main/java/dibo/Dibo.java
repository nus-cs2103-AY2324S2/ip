package dibo;

import dibo.command.Command;
import dibo.exception.DiboException;

/**
 * The Dibo class represents the Dibo chatbot.
 */
public class Dibo {
    private static final String FILE_PATH = "./data/dibo.txt";
    private boolean isBye;
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    /**
     * Constructs a new Dibo object.
     * @Throws DiboException If an exception occurs when loading the data from the text file.
     */
    public Dibo() throws DiboException {
        this.isBye = false;
        this.storage = new Storage(FILE_PATH);
        this.ui = new Ui();
        this.tasks = new TaskList(this.storage.loadData());
    }

    /**
     * Returns the String response of the Dibo chatbot based on the user input.
     * @param input The String representation of the user input.
     */
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
     * Returns a boolean value to signal if the bye command has been called.
     */
    public boolean hasEnded() {
        return isBye;
    }

}
