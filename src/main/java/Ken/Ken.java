package ken;

import ken.exception.KenException;
import ken.parser.Parser;
import ken.storage.Storage;
import ken.task.TaskList;
import ken.ui.Ui;

/**
 * The main class representing the Ken application.
 * Ken is a task management Chat-Bot application that allows users to
 * manage their tasks through a command-line interface.
 */
public class Ken {
    private static final String FILE_PATH = "./data/ken.txt";

    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    /**
     * Constructs a Ken object.
     *
     * @param filePath The file path for storing tasks.
     */
    public Ken(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTask());
        } catch (KenException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Ken application.
     * It displays a welcome message, initializes the parser, and
     * starts processing user commands until the user says goodbye.
     */
    public void run() {
        ui.welcomeMessage();
        Parser parser = new Parser(tasks, storage);
        try {
            parser.parseUserCommands();
        } catch (KenException e) {
            e.printStackTrace();
        } finally {
            if (parser.hasSaidBye()) {
                ui.byeMessage();
            }
        }
    }

    /**
     * The entry point of the Ken application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        new Ken(FILE_PATH).run();
    }
}
