import duke.ProgramState;
import duke.UI;
import task.Command;
import task.CommandParser;
import task.DukeException;
import task.Storage;
import task.StorageLoadException;
import task.StorageSaveException;
import task.TaskList;

public class Duke {
    private static final String chatbotName = "Sylvia";

    private static final String dataFilePath = "data/duke.txt";

    private Storage storage;
    private TaskList list;
    private UI ui;
    private ProgramState state;

    public Duke() {
        this.ui = new UI(chatbotName);
        this.state = new ProgramState();
        this.storage = new Storage(dataFilePath);
        try {
            list = storage.load();
        } catch (StorageLoadException e) {
            ui.showBotError(e);
            list = new TaskList();
        }
    }

    private boolean runCommand(String commandString) {
        CommandParser parser = new CommandParser();
        Command command;
        try {
            command = Command.parse(commandString, parser);
            boolean loopSignal = command.execute(list);
            return loopSignal;
        } catch (DukeException e) {
            ui.showBotError(e);
        }
        return true; // bot should continue running after invalid user input
    }

    public void run() {
        ui.showWelcomeMessage();
        boolean loopSignal = true;

        while (loopSignal) {
            String input = ui.readCommand();
            loopSignal = runCommand(input);
        }
        // only write data to file when the bot is about to exit
        try {
            storage.save(list);
        } catch (StorageSaveException e) {
            ui.showBotError(e);
        }
    }

    public static void main(String[] args) {
        Duke chatbot = new Duke();
        chatbot.run();
    }
}
