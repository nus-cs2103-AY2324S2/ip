package duke;

import java.io.IOException;

import javafx.application.Platform;


/**
 * The Duke class is the main class of the Duke application.
 * It is responsible for the initialization of the Storage and TaskList instances.
 * It also contains the main method that serves as the entry point for the Duke application.
 */
public class Duke {
    private static TaskList tasks;
    private Storage storage;

    /**
     * Constructor for Duke.
     * Initializes the Storage and TaskList instances.
     *
     * @param filePath The path to the file where the task list is stored.
     * @throws DukeException If an error occurs while loading the task list.
     */
    public Duke(String filePath) throws DukeException {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            Ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Processes the given input and returns a response.
     * <p>
     * This method handles user input and performs actions based on the input. If the input is "bye",
     * it attempts to save the current list of tasks to storage and then exits the platform. For other inputs,
     * it processes the input to generate a response. If an error occurs during saving or processing,
     * an appropriate error message is returned.
     *
     * @param input The user input to be processed.
     * @return A string representing the response to the input. This could be a confirmation message for actions
     *         taken (e.g., saving data, adding a task), an error message if something goes wrong, or any other
     *         response generated based on the user input.
     */
    public String getResponse(String input) {
        String response;
        assert input != null : "Input should not be null";
        if (input.equals("bye")) {
            try {
                Storage.saveCurrentList(tasks);
                Platform.exit();
            } catch (IOException e) {
                return "Error saving file";
            }
            return "Bye. Hope to see you again soon!";
        } else {
            try {
                response = Ui.parse(tasks, input);
            } catch (DukeException e) {
                return e.getMessage();
            }
        }
        return response;
    }


}
