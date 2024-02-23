package asher;

import asher.commands.Command;
import asher.commands.ExitCommand;
import asher.commands.Parser;
import asher.commands.Storage;
import asher.tasks.TaskList;
import asher.ui.Ui;

/**
 * Represents the main Asher class to run the program.
 */
public class Asher {

    private final Ui ui;
    private final TaskList taskList;
    private Storage storage;
    public boolean isExit = false;

    /**
     * Constructs an Asher object.
     *
     * @param ui The Ui object.
     * @param taskList The List of tasks.
     * @param storage The storage object.
     */
    public Asher(Ui ui, TaskList taskList, Storage storage) {
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
        String dataFile = "./taskLists.txt";
        storage.getFileContents(dataFile, taskList);
    }

    /**
     * Retrieves the response to user input.
     *
     * @param input The user input.
     * @return The response.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parseCommand(input);
            if (command instanceof ExitCommand) {
                isExit = true;
            } return command.execute(ui, taskList, storage);
        } catch (BotException e) {
            return ui.showErrorMessage("Error: " + e.getMessage());
        }
    }

    /**
     * Runs the Asher program.
     */
    public void run() {
    }

    /**
     * Main method to start the Asher program.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        Storage storage = new Storage();
        new Asher(ui, taskList, storage).run();
    }
}