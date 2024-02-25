package roland;

import command.Command;
import javafx.Launcher;


/**
 * The Duke class is the main class for the Roland task management application.
 * It initializes the UI, storage, and task list, and handles the main loop for user interactions.
 *
 * @author wolffe88
 */

public class Duke {

    private TaskList tasks;
    private final Ui ui;
    private final Storage storage;

    /**
     * Constructor to initialise an instance of Duke
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("./src/main/java/data/roland.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (RolandException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String response;
        try {
            Command c = Parser.parse(input);
            response = c.execute(tasks, ui, storage);
            System.out.println(response);
        } catch (RolandException e) {
            response = e.getMessage();
        }
        return response;
    }



    /**
     * The main method to start the Roland application.
     *
     * @param args The command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        Launcher.main(args);
    }
}
