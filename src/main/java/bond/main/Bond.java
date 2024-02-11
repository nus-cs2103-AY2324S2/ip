package bond.main;

import bond.command.Command;
import bond.task.TaskList;

/**
 * The main class for the Bond task management program.
 *
 * @author Benny Loh
 * @version 0.1
 */
public final class Bond {

    private Ui ui;

    private Storage storage;

    private TaskList taskList;

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
     * Constructor for the Bond class.
     *
     * @param filePath The file path to the file where the tasks are stored.
     */
    public Bond(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);

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

    /**
     * Serves as core method to run the Bond task management program.
     */
    public void run() {

        this.ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = this.ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(this.taskList, this.ui, this.storage);
                isExit = c.isExit();
            } catch (BondException e) {
                this.ui.showError(e);
            }
        }
    }

    /**
     * Serves as main entry point method to start the Bond task management program.
     *
     * @param args The arguments passed in to the program, not used.
     */
    public static void main(String[] args) {
        Bond bond = new Bond(System.getProperty("user.home") + "/data/Bond.txt");
        bond.run();
    }
}
