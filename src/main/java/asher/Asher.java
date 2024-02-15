package asher;

import asher.Commands.Command;
import asher.Commands.ExitCommand;
import asher.Commands.Parser;
import asher.Tasks.TaskList;
import asher.Ui.Ui;
import asher.Commands.Storage;

/**
 * Represents the main Asher class to run the program.
 */
public class Asher {

    private final Ui ui;
    private final TaskList taskList;
    private Storage storage;
    public boolean isExit = false;

    public Asher(Ui ui, TaskList taskList, Storage storage) {
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parseCommand(input);
            if (command instanceof ExitCommand) {
                isExit = true;
            }
            return command.execute(ui, taskList, storage);
        } catch (BotException e) {
            return ui.showErrorMessage("Error: " + e.getMessage());
        }
    }

    public void run() {
        String dataFile = "./taskList.txt";
        storage.getFileContents(dataFile, taskList);
    }

    public static void main(String[] args) {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        Storage storage = new Storage();
        new Asher(ui, taskList, storage).run();
    }
}