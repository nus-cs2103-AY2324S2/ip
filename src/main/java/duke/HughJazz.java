package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;


/**
 * The main class for the Duke application named HughJazz.
 * This class initializes the application and manages the main interaction loop, processing user inputs
 * and executing corresponding actions.
 */
public class HughJazz {
    /**
     * UI component responsible for interactions with the user.
     */
    private static Ui ui = new Ui();

    /**
     * Storage component responsible for loading and saving tasks to a file.
     */
    private static Storage storage = new Storage("." + File.separator + "data" + File.separator + "duke.txt");

    /**
     * TaskList component that holds and manages all tasks in the application.
     */
    private static TaskList taskList = new TaskList();

    /**
     * Initializes a new HughJazz instance.
     * This constructor initializes the main components of the Duke application.
     */
    public HughJazz() {}

    /**
     * Initializes the application by loading tasks from the storage file.
     * If the storage file is not found, an error message is displayed to the user.
     * This method should be called at the start of the application to ensure that
     *      any existing tasks are loaded into the application.
     */
    public void init() {
        try {
            ArrayList<Task> loadedTasks = storage.load();
            taskList.loadTasks(loadedTasks);
        } catch (FileNotFoundException e) {
            ui.showError("No existing txt file found.");
        }
    }

    /**
     * Processes the user input and returns a response.
     * This method takes a user input string, processes it to perform the appropriate actions,
     *      and returns a response string that can be displayed to the user. It handles parsing of the input,
     *      execution of commands, and error handling.
     *
     * @param input The user input string to be processed.
     * @return A response string resulting from processing the input.
     */
    public String getResponse(String input) {
        assert taskList != null : "TaskList is not initialized.";
        assert storage != null : "Storage is not initialized.";

        try {
            return Parser.parse(input, taskList, storage);
        } catch (DateTimeParseException e) {
            return "Please input date and time in the correct format " + Task.DATE_TIME_FORMAT_INPUT;
        } catch (ChatbotException e) {
            return e.getMessage();
        }
    }
}
