package cappy;

import cappy.command.CommandType;
import cappy.error.CappyException;
import cappy.parser.ParsedInput;
import cappy.parser.Parser;
import cappy.storage.Storage;
import cappy.task.TaskList;
import cappy.ui.Ui;

import java.io.IOException;

/**
 * The main class of the Cappy application.
 *
 * <p>The {@code Cappy} class is the entry point of the Cappy application. It initializes the
 * application components and starts the main input loop to interact with the user.
 */
public class Cappy {
    private static final String STORAGE_PATH = "./cappy.csv";
    private static final Ui UI = new Ui();
    private static TaskList taskList;
    private static Storage storage;

    public static void main(String[] args) {
        UI.showBanner();
        UI.showGreetings();
        try {
            storage = new Storage(STORAGE_PATH);
            taskList = TaskList.load(storage);
            inputLoop();
            storage.close();
        } catch (IOException | CappyException e) {
            UI.showError(e);
        } finally {
            Cappy.UI.close();
        }
    }

    private static void inputLoop() {
        String input = "";
        while (true) {
            input = UI.getInput();
            try {
                ParsedInput parsedInput = Parser.parse(input);
                parsedInput.executeCommand(taskList, UI, storage);
                if (parsedInput.getCommandType() == CommandType.BYE) {
                    break;
                }
            } catch (IOException | CappyException e) {
                UI.showError(e);
            }
        }
    }
}
