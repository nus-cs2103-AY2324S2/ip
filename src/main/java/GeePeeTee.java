
import java.io.FileNotFoundException;
import java.io.IOException;

import exception.GeePeeTeeException;
import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * Represents the main class of the GeePeeTee application.
 * <p>
 * This class is responsible for initializing the application and running the
 * main
 * loop of the application, which processes user input and executes the
 * corresponding commands.
 * </p>
 */
public class GeePeeTee {

    private TaskList taskList;
    private Storage storage;
    private Parser parser;
    private Ui ui;
    private String initializationErrorMessage = null; // Field to store initialization error message

    /**
     * Constructs a new {@code GeePeeTee} instance with the specified file path.
     * 
     * @param filePath The file path to be associated with the application.
     */
    public GeePeeTee(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            taskList = new TaskList(storage.loadTaskList());
            parser = new Parser(taskList, storage, ui);
        } catch (FileNotFoundException e) {
            initializationErrorMessage = ui.getFileNotFoundErrorMessage();
        } catch (IOException e) {
            initializationErrorMessage = ui.getLoadingErrorMessage();
        } catch (GeePeeTeeException e) {
            initializationErrorMessage = ui.getErrorMessage(e.getMessage());
        }
    }

    public String getInitializationErrorMessage() {
        return initializationErrorMessage;
    }

    public String getResponse(String input) {
        if (input.equals("bye")) {
            return ui.getGoodbyeMessage();
        }
        return parser.parseInput(input);
    }
}
