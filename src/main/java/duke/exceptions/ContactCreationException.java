package duke.exceptions;

/**
 * This class implements the exception for when an error occurs on creation of contacts.
 */
public class ContactCreationException extends DukeException {

    /**
     * Creates a ContactCreationException.
     *
     * @param errorMsg
     */
    public ContactCreationException(String errorMsg) {
        super("Error Creating Contact! " + errorMsg);
    }

}
