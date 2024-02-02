package duke.main;
import duke.command.Command;
import duke.exception.DukeException;

/**
 * Represents the main class for the chat application.
 */
public class Duke { 

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Main method for the chat application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        duke.startChat();
    }

    /**
     * Constructs a Duke instance with the given file path to store tasks.
     *
     * @param filePath The file path for storing tasks.
     */
    private Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        try {
            tasks = new TaskList(storage.loadList());
        }
        catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Starts the chat interface.
     */
    private void startChat() {
        ui.sayHi();
        boolean exited = false;
        while (!exited) {
            String fullCommand = ui.readCommand();
            try {
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                exited = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }
}