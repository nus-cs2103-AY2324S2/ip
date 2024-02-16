package grizzly.commands;

import grizzly.utils.Database;
import grizzly.utils.Storage;

/**
 * This class implements the commmand for listing tasks in a tasklist.
 */
public class ListTaskCommand extends Command {

    /**
     * Creates a ListTaskCommand, sets isExit to false.
     */
    public ListTaskCommand() {
        super(false);
    }

    /**
     * Executes list task command, uses ui to print out tasks in provided TaskList.
     *
     * @param db the current database of records.
     * @param storage Storage object with save file.
     */
    @Override
    public String execute(Database db, Storage storage) {
        return db.toString();
    }
}
