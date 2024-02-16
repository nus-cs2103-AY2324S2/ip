package duke;
import duke.command.Command;

/**
 * The main class of the chatbot.
 */
public class Duke {
    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;

    /**
     * Constructs the class Duke.
     *
     * @param filePath the path of the file that stores the chat history.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList();
        storage.load(taskList);
    }

    /**
     * Runs the chatbot.
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command command = Parser.parse(fullCommand);
            command.execute(taskList, ui, storage);
            isExit = command.isExit();
        }

    }

    /**
     * The main method.
     * Prepares the storage file and runs the chatbot.
     *
     * @param args The command line argument.
     */
    public static void main(String[] args) {
        new Duke("./data/storedTasks.txt").run();
    }
}
