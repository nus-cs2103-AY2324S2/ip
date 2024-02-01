package duke;

import command.Command;


/**
 * The Main class of this program.
 */
public class Duke {


    /** The Storage Manager to load and store the data to a file. */
    private Storage storage;

    /** The TaskList Object to store the list of tasks. */
    private TaskList tasks;

    /** The Ui Manager to handle the interaction with the user. */
    private Ui ui;

    /**
     * Creates a constructors and performs all the necessary work.
     *
     * @param filePath the name of the text file to be stored.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasksFromFile());
        } catch (DukeException e) {
            ui.printLoadingError();
            tasks = new TaskList();
        }
    }


    /**
     * Runs the program.
     */
    public void run() {
        ui.printWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String command = ui.readCommand();
                ui.printOpeningDottedLine();

                Command c = Parser.parse(command);
                c.executeCommand(tasks, ui, storage);
                isExit = c.getIsExit();
            } catch (DukeException e) {
                ui.printError(e.getMessage());
            } finally {
                ui.printClosingDottedLine();
            }
        }


    }


    /**
     * Runs the program.
     * The main entry point of the program.
     *
     * @param args
     */
    public static void main(String[] args) {
        new Duke("duke").run();
    }




}









