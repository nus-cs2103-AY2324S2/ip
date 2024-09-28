package chad;

import java.io.IOException;

import chad.command.Command;
import chad.exceptions.ChadException;
import chad.utility.Parser;
import chad.utility.Storage;
import chad.utility.TaskList;
import chad.utility.Ui;


/**
 * Represents a chatbot of the name Chad.
 */
public class Chad {
    private TaskList list;
    private Ui ui;
    private Storage storage;

    /**
     * Default Constructor for Chad setting default filepath of task list file to data/chad.txt.
     */
    public Chad() {
        ui = new Ui();
        storage = new Storage("data/chad.txt");
        try {
            list = new TaskList(storage.load());
        } catch (ChadException | IOException e) {
            ui.showError(e.getMessage());
            list = new TaskList();
        }
    }

    /**
     * Creates a Chad object that can run.
     *
     * @param filePath a filepath where Chad stores a {@link TaskList} in
     */
    public Chad(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            list = new TaskList(storage.load());
        } catch (ChadException | IOException e) {
            ui.showError(e.getMessage());
            list = new TaskList();
        }
    }

    private void run() {
        ui.greet();
        boolean end = false;
        while (!end) {
            try {
                String input = ui.readInput();
                Command c = Parser.parse(input);
                c.execute(list, ui, storage);
                end = c.isExit();
            } catch (ChadException e) {
                ui.showError(e.getMessage());
            }
        }
        assert end == true;
    }


    /**
     * Gets a response upon an input from the user.
     *
     * @param input the user input
     * @return response to the user
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(list, ui, storage);
            return ui.craftResponse();
        } catch (ChadException e) {
            ui.showError(e.getMessage());
            return ui.craftResponse();
        }
    }

    /**
     * Chad greets user with introduction.
     *
     * @return the greeting output
     */
    public String greet() {
        this.ui.greet();
        return ui.craftResponse();
    }

    public static void main(String[] args) {
        new Chad("data/chad.txt").run();
    }
}

