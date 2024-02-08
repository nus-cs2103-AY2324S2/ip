import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import duke.command.DukeException;
import duke.command.Parser;
import duke.command.Storage;
import duke.command.TaskList;
import duke.command.Ui;

/**
 * Main class for Duke chatbox.
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Duke constructor.
     *
     * @param filePath
     * @throws IOException
     */
    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            taskList = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Main driver code for Duke chatbox.
     * Handles calls to TaskList, Ui, Storage and Parser.
     *
     * @throws DukeException
     * @throws IOException
     */
    public void run() throws DukeException, IOException {
        Parser parser = new Parser();
        ui.printWelcomeMessage();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int exit = parser.parseInput(br.readLine(), storage, taskList, ui);
            ui.printLine();

            while (exit != 0) {
                exit = parser.parseInput(br.readLine(), storage, taskList, ui);
                ui.printLine();
            }
        } catch (DukeException de) {
            ui.printErrorMessage(de.getErrorMessage());
        }

        ui.printExitMessage();
    }

    public static void main(String[] args) throws DukeException, IOException {
        new Duke("data/duke.txt").run();
    }
}
