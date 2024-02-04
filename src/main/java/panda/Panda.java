package panda;
import panda.command.Command;
import panda.component.Parser;
import panda.component.Storage;
import panda.component.TaskList;
import panda.component.Ui;
import panda.exception.PandaException;

public class Panda {

    private TaskList tlist;
    private static final String filePath = "./src/main/list.txt";
    private Storage cacheFile;
    private Ui ui;

    /**
     * Constructs a new Panda instance.
     * Initializes the user interface, storage file, and task list.
     * 
     * @param filePath the path to the file where tasks are stored.
     */
    public Panda(String filePath) {
        ui = new Ui();
        cacheFile = new Storage(filePath);
        try {
            tlist = new TaskList(cacheFile.load());
        } catch (PandaException e) {
            ui.showLoadingError();
            tlist = new TaskList();
        }
    }

    /**
     * Runs the Panda application.
     * Displays a welcome message, reads and executes commands until the exit command is received, and handles exceptions.
     */
    private void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tlist, ui, cacheFile);
                isExit = c.isExit();
            } catch (PandaException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Entry point for the Panda application.
     * Creates a new Panda instance and runs it.
     * 
     * @param args command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Panda(filePath).run();
    }
}
