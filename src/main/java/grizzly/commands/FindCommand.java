package grizzly.commands;

import java.util.Hashtable;

import grizzly.contacts.Contact;
import grizzly.exceptions.MissingInformationException;
import grizzly.tasks.Task;
import grizzly.utils.Database;
import grizzly.utils.Storage;

/**
 * This class implements the find Command, when executed, it searches for tasks matching user input in the
 * given TaskList.
 *
 * @author delishad21
 */
public class FindCommand extends Command {

    private String description;

    /**
     * Creates a find command object to find tasks in tasklist based on matching string.
     *
     * @param params includes string to be matched to task.
     */
    public FindCommand(Hashtable<String, String> params) {
        super(false);
        this.description = params.get("description");
    }

    /**
     * Executes the find command, finds item in the TaskList provided.
     *
     * @param db the current database of records.
     * @param storage Storage object with save file.
     */
    @Override
    public String execute(Database db, Storage storage) throws MissingInformationException {
        if (description.equals("")) {
            throw new MissingInformationException("description");
        }

        String toFind = description.trim();
        String foundRecords = "";

        foundRecords += "----------\nTasks\n----------\n";

        for (int i = 1; i <= db.taskListSize(); i++) {
            Task task = db.getTask(i);
            if (task.getDescription().contains(toFind)) {
                foundRecords += i + "." + task + "\n";
            }
        }

        foundRecords += "----------\nContacts\n----------\n";

        for (int i = 1; i <= db.contactListSize(); i++) {
            Contact contact = db.getContact(i);
            if (contact.toString().contains(toFind)) {
                foundRecords += i + "." + db.getContact(i - 1) + "\n";
            }
        }

        return foundRecords;

    }
}
