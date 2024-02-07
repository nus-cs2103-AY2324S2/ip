package luke;

import luke.exception.FileException;
import luke.exception.LukeException;
import luke.parser.Parser;
import luke.task.TaskList;
import luke.ui.Ui;

/**
 * Represents the main class of the program.
 */
public class Luke {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private static final String FILE_PATH = "./src/main/data/luke.txt";

    /**
     * Constructor for Luke that initializes the Ui, Storage and TaskList.
     *
     * @param filePath The file path of the file to be read from.
     */
    public Luke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readTask());

        } catch (FileException e) {
            ui.getErrorMessage(e.getMessage());
            tasks = new TaskList();
        }

    }

    /**
     * Runs the program.
     */
    public void run() {
        // Greetings
        ui.welcome();

        boolean isExit = false;
        Parser parser = new Parser("");

        // Conditions
        while (!isExit) {
            String inputString = ui.readCommand();
            parser.setInput(inputString);
            parser.parse(tasks, ui, storage);
            isExit = parser.isExit();

        }

        // Bye and exits
        ui.goodbye();
    }

    /**
     * Main method to run the program.
     *
     * @param args The arguments passed in.
     * @throws LukeException If an error occurs.
     */
    public static void main(String[] args) throws LukeException {
        new Luke(FILE_PATH).run();

    }
}
