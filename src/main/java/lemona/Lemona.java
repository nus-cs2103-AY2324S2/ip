package lemona;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import lemona.command.Command;
import lemona.exceptions.MissingDescriptionException;
import lemona.exceptions.MissingIndexException;
import lemona.oop.Ui;
import lemona.oop.Parser;
import lemona.oop.Storage;
import lemona.oop.TaskList;

/**
 * The main class representing the Lemona task manager application.
 * Lemona allows users to manage their tasks by adding, listing, marking, and deleting tasks.
 * It also provides the functionality to save tasks to a file and load tasks from a file.
 */
public class Lemona {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Lemona object with the specified file path for task storage.
     *
     * @param filePath The file path to store tasks.
     */
    public Lemona(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (DateTimeParseException e) {
            ui.showDateTimeParsingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Lemona application.
     * Displays a welcome message and continuously waits for user input.
     * Processes user commands and performs corresponding actions.
     * Exits the application when the "bye" command is entered.
     */
    public String getResponse(String input) {
        try {
            Command parsedInput = Parser.parse(input);
            String str =  parsedInput.execute(tasks);
            storage.save(tasks);
            return str;
        } catch (MissingIndexException e) {
            return e.toString(input.split(" ")[0]);
        } catch (MissingDescriptionException e) {
            return e.toString(input.split(" ")[0]);
        } catch (IOException e) {
            return e.toString();
        }
    }
}
