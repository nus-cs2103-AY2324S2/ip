package echon;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents the main class of the application.
 */
public class Echon {
    private static final String FILE_PATH = "data/echon.txt";
    private static final ArrayList<String> WELCOME_MESSAGES = new ArrayList<String>(
            Arrays.asList("Hello! I'm Echon", "What can I do for you?"));

    private TaskList taskList;
    private CommandCreator commandCreator;
    private Storage storage;
    private EchonUi ui;

    /**
     * Creates a new instance of Echon.
     */
    public Echon(EchonUi ui) {
        this.ui = ui;
        try {
            this.storage = new Storage(FILE_PATH);
            this.taskList = storage.load();
        } catch (EchonException e) {
            this.ui.displayEchonMessage("Error loading task list: " + e.getMessage());
            return;
        }
        this.commandCreator = new CommandCreator(taskList);
        ui.displayEchonMessages(WELCOME_MESSAGES);
    }

    /**
     * Processes the commands entered by the user.
     *
     * @param input The command to be processed.
     */
    public void processCommand(String input) {
        try {
            Command command = this.commandCreator.createCommand(input);
            command.execute(ui);
            this.storage.save();
        } catch (EchonException e) {
            this.ui.displayEchonMessage(e.getMessage());
        }
    }
}
