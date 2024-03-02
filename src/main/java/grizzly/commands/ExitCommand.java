package grizzly.commands;

import java.io.IOException;

import grizzly.utils.Database;
import grizzly.utils.Storage;

/**
 * This class implements the exit command that leads to the exit of the bot when executed.
 *
 * @author delishad21
 */
public class ExitCommand extends Command {

    /**
     * Creates an exit command, sets isExit to true.
     */
    public ExitCommand() {
        super(true);
    }



    /**
     * Executes exit command, saves data into save file using storage object.
     *
     * @param db the current database of records.
     * @param storage Storage object with save file.
     */
    @Override
    public String execute(Database db, Storage storage) {

        if (storage == null) {
            return "Data not saved: Storage initialisation not sucessful";
        }

        try {
            return storage.saveData(db);
        } catch (IOException e) {
            return "Data not saved: " + e.getMessage();
        }
    }
}
