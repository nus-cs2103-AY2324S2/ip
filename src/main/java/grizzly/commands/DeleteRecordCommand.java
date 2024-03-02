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

        String[] params = splitParams();

        String recordType = params[0];
        int index = Integer.parseInt(params[1]);

        switch (recordType) {
        case "task":
            return deleteTask(db, index);
        case "contact":
            return deleteContact(db, index);
        default:
            throw new RecordDeletionException("No Such Record Type!");
        }

    }

    private String[] splitParams() throws RecordDeletionException {
        String[] descSplit = description.split(" ", 2);
        if (descSplit.length < 2) {
            throw new RecordDeletionException("Insufficient information for delete!");
        }

        try {
            Integer.parseInt(descSplit[1]);
        } catch (NumberFormatException e) {
            throw new RecordDeletionException("Given index " + descSplit[1]
                                              + " is not a valid index");
        }

        return descSplit;
    }

    /**
     * Deletes task from database based on index.
     *
     * @param db database to delete from
     * @param index index of task to delete
     * @return status of removal
     * @throws RecordDeletionException if index is out of bounds
     */
    private String deleteTask(Database db, int index) throws RecordDeletionException {

        if (index < 1 || index > db.taskListSize()) {
            throw new RecordDeletionException("Given index " + index + " out of bounds\n"
                                              + "taskList current at size: " + db.taskListSize());
        }

        Task task = db.getTask(index);
        db.removeTask(index);
        return "Task \"" + task + "\" has been removed!";


    }
    /**
     * Deletes contact from database based on index.
     *
     * @param db database to delete from
     * @param index index of task to delete
     * @return status of removal
     * @throws RecordDeletionException if index is out of bounds
     */
    private String deleteContact(Database db, int index) throws RecordDeletionException {

        if (index < 1 || index > db.contactListSize()) {
            throw new RecordDeletionException("Given index " + index + " out of bounds\n"
                                              + "contactList current at size: " + db.contactListSize());
        }

        Contact contact = db.getContact(index);
        db.removeContact(index);

        return "Contact \"" + contact + "\" has been removed!";
    }

}
