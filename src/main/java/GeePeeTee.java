
import java.io.FileNotFoundException;
import java.io.IOException;

import command.CommandResponse;
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

    private TaskList listOfTask;
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
            assert storage != null : "Storage was not initialised correctly";
            listOfTask = new TaskList(storage.loadTaskList());
            assert listOfTask != null : "Task list was not initialised correctly";
            parser = new Parser(listOfTask, storage, ui);
            assert parser != null : "Parser was not initialised correctly";
        } catch (FileNotFoundException e) {
            initializationErrorMessage = ui.getFileNotFoundErrorMessage();
        } catch (IOException e) {
            initializationErrorMessage = ui.getLoadingErrorMessage();
        } catch (GeePeeTeeException e) {
            initializationErrorMessage = ui.getErrorMessage(e.getMessage());
        }
    }

    /**
     * Gets the error message that occurred during initialization.
     * 
     * @return The error message that occurred during initialization
     */
    public String getInitializationErrorMessage() {
        return initializationErrorMessage;
    }

    /**
     * Processes the user input and returns the response.
     * 
     * @param input The user input to be processed
     * @return The response to the user input
     */
    public CommandResponse getResponse(String input) {
        return parser.parseInput(input);
    }
}
