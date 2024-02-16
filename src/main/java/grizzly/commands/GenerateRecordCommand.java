package grizzly.commands;

import java.time.format.DateTimeParseException;
import java.util.Hashtable;

import grizzly.contacts.Contact;
import grizzly.exceptions.ContactCreationException;
import grizzly.exceptions.MissingInformationException;
import grizzly.exceptions.MissingParameterException;
import grizzly.tasks.Deadline;
import grizzly.tasks.Event;
import grizzly.tasks.Todo;
import grizzly.utils.Database;
import grizzly.utils.Storage;


/**
 * This class implements a command that generates records (Tasks or Contacts) based on RecordType.
 */
public class GenerateRecordCommand extends Command {
    /**
     * Enum for different Record types.
     */
    public static enum RecordType { TODO, DEADLINE, EVENT, CONTACT }

    private RecordType recordType;
    private Hashtable<String, String> params;

    /**
     * Creates GenerateRecordCommand, basic constructor that takes in the type of record and the user input.
     *
     * @param taskType The type of record to be generated based on the enum RecordType.
     * @param input user input to be parsed.
     */
    public GenerateRecordCommand(RecordType recordType, Hashtable<String, String> params) {
        super(false);
        this.recordType = recordType;
        this.params = params;
    }

    /**
     * Executes record generation command, generates record based on user input and recordType.
     *
     * @param db the database of records.
     * @param storage Storage object with save file.
     */
    @Override
    public String execute(Database db, Storage storage)
            throws MissingInformationException, MissingParameterException,
            DateTimeParseException, ContactCreationException {
        switch (this.recordType) {
        case TODO:
            Todo t = Todo.todoParse(false, params);
            db.addTask(t);
            return "Todo Task added!\n" + t.toString() + "\n"
                   + "You now have " + db.taskListSize() + " tasks in the list.";
        case DEADLINE:
            Deadline d = Deadline.deadlineParse(false, params);
            db.addTask(d);
            return "Deadline Task added!\n" + d.toString() + "\n"
                   + "You now have " + db.taskListSize() + " tasks in the list.";
        case EVENT:
            Event e = Event.eventParse(false, params);
            db.addTask(e);
            return "Event Task added!\n" + e.toString() + "\n"
                   + "You now have " + db.taskListSize() + " tasks in the list.";
        case CONTACT:
            Contact c = Contact.contactParse(params);
            db.addContact(c);
            return "Contact added!\n" + c.toString() + "\n"
                   + "You now have " + db.contactListSize() + " contacts.";
        default:
            return "Error generating task: No such task type";
        }
    }
}
