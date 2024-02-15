package bond.main;

import bond.command.Command;
import bond.task.TaskList;

/**
 * The Bond entity class for the Bond task management program.
 *
 * @author Benny Loh
 * @version 0.1
 */
public final class Bond {

    private Ui ui;

    private Storage storage;

    private TaskList taskList;

    /**
     * Constructor for the Bond class.
     */
    public Bond() {
        this.ui = new Ui();
        this.storage = new Storage(System.getProperty("user.home") + "/data/Bond.txt");

        try {
            this.taskList = new TaskList(this.storage.load());
        } catch (BondException e) {
            ui.showError(e);
            this.taskList = new TaskList(null);
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String response = c.execute(this.taskList, this.ui, this.storage);
            return response;
        } catch (BondException e) {
            return e.getMessage();
        }
    }
}
