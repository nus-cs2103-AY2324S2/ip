package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;

import duke.commands.Command;

import duke.exceptions.NoSuchCommandException;
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
     * Creates Duke object with filepath to data file.
     *
     * @param filePath Filepath to save file.
     */
    public Duke() {
        this.tasks = new TaskList();
        this.storage = null;
        this.isRunning = true;
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    public String loadSave(String filePath) {
        if (isRunning) {
            try {
                this.storage = new Storage(filePath);
                this.tasks = storage.readSaveData();
                return tasks.size() + "Tasks loaded from save";
            } catch (FileNotFoundException e) {
                return "Error reading file: " + e.getMessage() + "\nMaking new task list";
            } catch (IOException e) {
                return "Save file could not be generated: " + e.getMessage() + "\nMaking new task list";
            }
        }

        return "Duke is not running";
    }

    public String getResponse(String input) {
        if (isRunning) {
            try {
                Command c = Parser.parse(input);
                String response = c.execute(tasks, storage);
                if (storage != null) {
                    storage.saveTodoData(tasks);
                }
                if (c.isExit) {
                    this.isRunning = false;
                }
                return response;
            } catch (NoSuchCommandException e) {
                return e.getMessage();
            } catch (TaskCreationException e) {
                return "Error Creating Task: " + e.getMessage();
            } catch (DateTimeParseException e) {
                return "Error parsing datetime: " + e.getMessage()
                    + "\nUse the format \"DD/MM/YYYY, HH:MM\" to enter date and time.";
            } catch (IndexOutOfBoundsException e) {
                return e.getMessage();
            } catch (NumberFormatException e) {
                return "Invalid selection for marking or deletion: " + e;
            } catch (TaskModificationException e) {
                return "Error Modifying Task: " + e.getMessage();
            } catch (IOException e) {
                return e.getMessage();
            }
        }

        return "Duke is not running";
    }
}
