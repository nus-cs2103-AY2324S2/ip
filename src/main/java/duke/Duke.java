package duke;

import command.Command;
import duke.Ui;
import duke.Storage;
import duke.TaskList;

/**
 * The Main class of this program.
 */
public class Duke {



    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a constructors and performs all the necessary work.
     * @param filePath the name of the text file to be stored.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasksFromFile());
        } catch (DukeException e) {
            ui.showLoadingError();
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
                c.excuteCommand(tasks, ui, storage);
                isExit = c.getIsExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.printClosingDottedLine();
            }
        }


    }




    public static void main(String[] args) {
        new Duke("duke").run();
    }




}









