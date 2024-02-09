package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.utils.Database;
import duke.utils.Parser;
import duke.utils.Storage;

/**
 * This class implements the functionality of the Duke bot.
 * @author delishad21
 * @version 0.1
 */
public class Duke {

    private Storage storage;
    private Database db;
    private boolean isRunning;

    /**
     * Initialises Duke object with an empty tasklist and empty storage.
     */
    public Duke() {
        this.db = new Database();
        this.storage = null;
        this.isRunning = true;
    }

    /**
     * Returns whether the Duke bot is still running.
     *
     * @return running state of Duke.
     */
    public boolean isRunning() {
        return this.isRunning;
    }

    /**
     * Loads filePath into a storage object for loading and saving files.
     *
     * @param filePath Path for file to be saved and loaded from.
     * @return Status of loading filepath.
     */
    public String loadSave(String filePath) {

        if (isRunning) {

            String status = "";

            try {
                this.storage = new Storage(filePath);
                status = storage.readSaveData(this.db) + "\n";
                status = status + db.taskListSize() + " Tasks loaded from save\n";
                status = status + db.contactListSize() + " Contacts loaded from save\n";
            } catch (FileNotFoundException e) {
                status = "Error reading file: " + e.getMessage() + "\nMaking new database";
            } catch (IOException e) {
                status = "Save file could not be generated: " + e.getMessage() + "\nMaking new database";
            }

            return status;

        }

        assert !this.isRunning();
        return "Duke is not running";
    }

    /**
     * Parses user input into commands, executes them and returns bot response.
     *
     * @param input User input.
     * @return Response from bot.
     */
    public String getResponse(String input) {

        if (isRunning) {
            try {
                Command c = Parser.parse(input);
                String response = c.execute(db, storage);
                if (storage != null) {
                    storage.saveData(db);
                }
                if (c.isExitCommand()) {
                    this.isRunning = false;
                }
                return response;
            } catch (DateTimeParseException e) {
                return "Error parsing datetime: " + e.getMessage()
                        + "\nUse the format \"DD/MM/YYYY, HH:MM\" to enter date and time.";
            } catch (IOException e) {
                return e.getMessage();
            } catch (DukeException e) {
                return e.getMessage();
            }
        }


        assert !this.isRunning();
        return "Duke is not running";
    }
}
