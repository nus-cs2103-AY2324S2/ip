package duke;

import duke.state.ProgramState;
import duke.command.Command;
import duke.command.CommandParser;
import duke.ui.UI;
import duke.storage.Storage;
import duke.storage.StorageLoadException;
import duke.storage.StorageSaveException;
import duke.task.TaskList;

public class Duke {
    private static final String chatbotName = "Sylvia";

    private static final String dataFilePath = "data/duke.txt";

    private Storage storage;
    private TaskList list;
    private UI ui;
    private ProgramState state;
    private CommandParser parser;

    public Duke() {
        this.parser = new CommandParser();
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

    private String runCommand(String commandString) {
        Command command;
        try {
            command = Command.parse(commandString, parser);
            String response = command.execute(list, state);
            return response;
        } catch (DukeException e) {
            ui.showBotError(e);
        }
        return null; // should not be shown
    }

    public void run() {
        ui.showWelcomeMessage();

        while (state.isNormal()) {
            String input = ui.readCommand();
            ui.showResponse(runCommand(input));
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
