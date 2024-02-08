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
     * The entry point of the application. It initializes necessary components,
     * loads existing tasks from storage, and enters a loop to accept and process user commands.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        ui.showGreeting();
        try {
            ArrayList<Task> loadedTasks = storage.load();
            taskList.loadTasks(loadedTasks);
        } catch (FileNotFoundException e) {
            ui.showError("No existing txt file found.");
        }

        String userInput;

        while (true) {
            userInput = ui.readCommand();
            if ("bye".equalsIgnoreCase(userInput)) {
                break;
            } else {
                try {
                    Parser.parse(userInput, taskList, storage);
                } catch (DateTimeParseException e) {
                    System.out.println("Please use the format dd/MM/yyyy HHmm for dates.");
                    System.out.println("Please try again.");
                } catch (ChatbotException e) {
                    ui.showError(e.getMessage());
                }
            }
        }
        ui.showGoodbye();
    }
}
