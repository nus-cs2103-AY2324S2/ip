package bond.main;

import bond.command.Command;
import bond.task.TaskList;

/**
 * The Bond entity class for the Bond task management program.
 *
 * @author Benny Loh
 * @version 0.1
 */
public class Bond {

    protected Ui ui;

    private final Storage storage;

    private TaskList taskList;

    /**
     * Constructor for the Bond class.
     */
    public Bond() throws BondException {
        this.ui = new Ui();
        this.storage = new Storage(System.getProperty("user.home") + "/data/Bond.txt");

        try {
            this.taskList = new TaskList(this.storage.load());
        } catch (BondException e) {
            this.taskList = new TaskList(null);
            BondException.raiseException("load", "LOAD_FAILURE");
        }
    }

    /**
     * Generates a response to user input.
     */
    protected String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(this.taskList, this.ui, this.storage);
        } catch (BondException e) {
            return e.getMessage();
        }
    }
}
