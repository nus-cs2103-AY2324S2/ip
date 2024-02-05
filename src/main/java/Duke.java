import roland.*;
import command.*;

public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;


    /**
     * Constructs a new Duke object with the specified file path for storage.
     *
     * @param filePath The file path for storing task data.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (RolandException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main loop of the Roland application, handling user interactions and commands.
     */
    public void run() {
        ui.boot();
//        System.out.println(System.getProperty("user.dir"));
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.spacer();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (RolandException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.spacer();
            }
        }
    }

    /**
     * The main method to start the Roland application.
     *
     * @param args The command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        new Duke("./src/main/java/data/roland.txt").run();
    }
}