import duke.command.Command;
import duke.command.CommandType;
import duke.helpers.Parser;
import duke.helpers.Storage;
import duke.helpers.Ui;
import duke.task.TaskList;

/**
 * Duke class
 */
public class Duke {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    /**
     * Constructor of Duke.
     */
    public Duke() {
        this.ui = new Ui();
        this.tasks = new TaskList();
        this.storage = new Storage(CommandType.FILEPATH.toString());
        this.storage.getStorageFromHardDisk(this.tasks);
    }


    /**
     * Returns response message.
     * @param userInput
     * @return String representation of response message.
     */
    public String getResponseMessage(String userInput) {
        assert userInput != null;
        Command command = Parser.parse(userInput);
        return command.getExecutionMessage(tasks, ui, storage);
    }
}
