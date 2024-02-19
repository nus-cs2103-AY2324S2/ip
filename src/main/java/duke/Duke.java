
package duke;

import java.util.Scanner;

/**
 * The Duke class represents the main entry point for the Duke program.
 * It initializes the user interface, storage, and task list, and runs the program.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private final Ui ui;


    /**
     * Constructs a Duke object with the specified file path for storing tasks.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Constructs a Duke object with the specified file path for storing tasks.
     *
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("./data/tasks.txt");
        tasks = new TaskList(storage.load());
    }

    /**
     * Runs the Duke program.
     * Displays the greeting message, initializes a scanner for user input,
     * and starts the parsing process.
     */

    public String getResponse(String input) {
        Parser parser = new Parser(tasks, storage);
        String bearduckyresponse = parser.read(input);
        return bearduckyresponse;
    }

}
