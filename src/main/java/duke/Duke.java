package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.exceptions.TaskCreationException;
import duke.exceptions.TaskModificationException;
import duke.utils.Parser;
import duke.utils.Storage;
import duke.utils.TaskList;

/**
 * This class implements the functionality of the Duke bot.
 * @author delishad21
 * @version 0.1
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private boolean isRunning;

    /**
     * Initialises Duke object with an empty tasklist and empty storage.
     */
    public Duke() {
        this.tasks = new TaskList();
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
            try {
                this.storage = new Storage(filePath);
                this.tasks = storage.readSaveData();
                return tasks.size() + " Tasks loaded from save";
            } catch (FileNotFoundException e) {
                return "Error reading file: " + e.getMessage() + "\nMaking new task list";
            } catch (IOException e) {
                return "Save file could not be generated: " + e.getMessage() + "\nMaking new task list";
            }
        }

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
                String response = c.execute(tasks, storage);
                if (storage != null) {
                    storage.saveTodoData(tasks);
                }
                if (c.isExitCommand()) {
                    this.isRunning = false;
                }
                return response;
            } catch (TaskCreationException e) {
                return "Error Creating Task! " + e.getMessage();
            } catch (DateTimeParseException e) {
                return "Error parsing datetime: " + e.getMessage()
                        + "\nUse the format \"DD/MM/YYYY, HH:MM\" to enter date and time.";
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace(System.out);
                return e.getMessage();
            } catch (NumberFormatException e) {
                return "Invalid selection for marking or deletion: " + e;
            } catch (TaskModificationException e) {
                return "Error Modifying Task: " + e.getMessage();
            } catch (IOException e) {
                return e.getMessage();
            } catch (DukeException e) {
                return e.getMessage();
            }
        }

        return "Duke is not running";
    }
}
