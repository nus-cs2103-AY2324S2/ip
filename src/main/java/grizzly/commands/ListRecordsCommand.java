package grizzly.commands;

import java.util.Hashtable;

import grizzly.exceptions.GrizzlyException;
import grizzly.utils.Database;
import grizzly.utils.Storage;

/**
 * This class implements the commmand for listing records in a tasklist.
 */
public class ListRecordsCommand extends Command {

    private String description;
    /**
     * Creates a ListTaskCommand, sets isExit to false.
     */
    public ListRecordsCommand(Hashtable<String, String> params) {
        super(false);
        this.description = params.get("description");
    }

    /**
     * Executes list task command, returns tasks and contacts in provided TaskList.
     *
     * @param db the current database of records.
     * @param storage Storage object with save file.
     */
    @Override
    public String execute(Database db, Storage storage) throws GrizzlyException {
        switch (description.toLowerCase()) {
        case "tasks":
            return db.tasksToString();
        case "contacts":
            return db.contactsToString();
        case "":
            return db.toString();
        default:
            throw new GrizzlyException("Error: " + description + "is not stored in my database!");
        }
    }
}
