package Duke;
import Duke.command.*;

/**
 * The main class of the application. This class initialize the application,
 * receive commands from users, process commands, and provide user feedbacks
 */
public class Duke {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Construct Duke instance, initialize application's components.
     *
     * @param filePath the path the program restore previously stored tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Start the application, use the loop to get user's input and process
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                Ui.print_message(e.getMessage());
            }
        }
    }

    public String getResponse(String userInput) {
        Command c = Parser.parse(userInput);
        String response = "";
        try {
            response = c.execute(tasks, ui, storage);
        } catch (RuntimeException e) {
            response = Ui.showException(e);
        }
        return response;
    }

    /**
     * The entry point of the entire program, create a Duke instance and run it.
     *
     * @param args parameter not in use.
     */
    public static void main(String[] args) {
        new Duke("database.ser").run();
    }
}
