package grizzly.commands;

import java.util.Hashtable;

import grizzly.contacts.Contact;
import grizzly.exceptions.RecordDeletionException;
import grizzly.tasks.Task;
import grizzly.utils.Database;
import grizzly.utils.Storage;

/**
 * This class implements the command that deletes a contact when executed.
 */
public class DeleteRecordCommand extends Command {

    private String description;

    /**
     * Creates a DeleteRecordCommand, deletes a record from the database when executed.
     *
     * @param params
     */
    public DeleteRecordCommand(Hashtable<String, String> params) {
        super(false);
        this.description = params.get("description");
    }

    /**
     * Executes the DeleteRecordCommand and deletes a record.
     */
    @Override
    public String execute(Database db, Storage storage) throws RecordDeletionException {
        String[] descSplit = description.split(" ", 2);
        if (descSplit.length < 2) {
            throw new RecordDeletionException("Insufficient information for delete!");
        }

        String recordType = descSplit[0];
        int index = Integer.parseInt(descSplit[1]);

        switch (recordType) {
        case "task":
            if (index < 1 || index > db.taskListSize()) {
                throw new RecordDeletionException("Given index " + index + " out of bounds\n"
                                                  + "taskList current at size: " + db.taskListSize());
            }
            Task task = db.getTask(index);
            db.removeTask(index);
            return "Task \"" + task + "\" has been removed!";
        case "contact":
            if (index < 1 || index > db.contactListSize()) {
                throw new RecordDeletionException("Given index " + index + " out of bounds\n"
                                                  + "contactList current at size: " + db.contactListSize());
            }
            Contact contact = db.getContact(index);
            db.removeContact(index);
            return "Contact \"" + contact + "\" has been removed!";
        default:
            throw new RecordDeletionException("No Such Record Type!");
        }

    }

}
