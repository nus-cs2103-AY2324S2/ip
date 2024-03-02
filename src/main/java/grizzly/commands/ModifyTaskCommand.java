package grizzly.commands;

import java.util.Hashtable;

import grizzly.exceptions.TaskModificationException;
import grizzly.tasks.Task;
import grizzly.utils.Database;
import grizzly.utils.Storage;

/**
 * This class implements the modify task command that modifies tasks in the bot tasklist.
 */
public class ModifyTaskCommand extends Command {
    /**
     * Enum used for classifying task modification types
     */
    public enum ModificationTypes { MARK, UNMARK }
    private ModificationTypes modType;
    private String indexInput;

    /**
     * Creates ModifyTaskCommand, takes in type of modificaiton and the user input for index to be modified.
     *
     *
     * @param modType Modification type based on enum ModificationTypes.
     * @param indexInput user input to be parsed into index.
     */
    public ModifyTaskCommand(ModificationTypes modType, Hashtable<String, String> params) {
        super(false);
        this.modType = modType;
        this.indexInput = params.get("description");
    }

    /**
     * Executes modify task command, modifies tasks in list based on index and type.
     *
     *
     * @param db the current database of records.
     * @param storage Storage object with save file.
     */
    @Override
    public String execute(Database db, Storage storage)
            throws TaskModificationException {

        int index = parseIndex(db);

        switch (this.modType) {
        case MARK:
            Task t1 = db.getTask(index);
            t1.doTask();
            assert t1.toString().substring(3, 6).equals("[X]");
            return "Good job on finishing your task!:\n" + t1;
        case UNMARK:
            Task t2 = db.getTask(index);
            t2.undoTask();
            assert t2.toString().substring(3, 6).equals("[ ]");
            return "I've marked this task as undone:\n" + t2;
        default:
            return "Error Modifying Task: No such modification type";
        }
    }

    /**
     * Parses indexInput into an integer index.
     *
     * @throws TaskModificationException
     */
    private int parseIndex(Database db) throws TaskModificationException {

        int index;

        // check if index is empty
        if (indexInput.equals("")) {
            throw new TaskModificationException("Input is missing task number\nList is of current length: "
                                                + db.taskListSize());
        }

        // check if index is a number
        try {
            index = Integer.parseInt(indexInput);
        } catch (NumberFormatException e) {
            throw new TaskModificationException("Invalid number input");
        }

        // check if index is within bounds
        if (index < 1 || index > db.taskListSize()) {
            throw new TaskModificationException("Invalid Index " + index
                                                + " for current list\nList is of current length: "
                                                + db.taskListSize());
        }

        return index;
    }
}
