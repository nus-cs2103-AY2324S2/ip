import duke.command.Command;
import duke.command.CommandType;
import duke.helpers.Parser;
import duke.helpers.Storage;
import duke.helpers.Ui;
import duke.task.TaskList;

/**
 * ChatPal class
 */
public class ChatPal {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;
    private Storage secondaryStorage;

    /**
     * Constructor of ChatPal.
     */
    public ChatPal() {
        this.ui = new Ui();
        this.tasks = new TaskList();
        this.storage = new Storage(CommandType.FILEPATH.toString());
        this.storage.getStorageFromHardDisk(this.tasks);
        this.secondaryStorage = new Storage(CommandType.SECONDARYFILEPATH.toString());
    }


    /**
     * Returns response message.
     * @param userInput
     * @return String representation of response message.
     */
    public String getResponseMessage(String userInput) {
        assert userInput != null;
        Command command = Parser.parse(userInput);
        return command.getExecutionMessage(tasks, ui, storage, secondaryStorage);
    }
}
