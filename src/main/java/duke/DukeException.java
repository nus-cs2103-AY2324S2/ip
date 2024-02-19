package duke;

/**
 * Represents the class of exception thrown by Duke Chatbot.
 */
public class DukeException extends Exception {

    /**
     * Constructs a DukeException with a specific error message.
     *
     * @param errormessage The message that tells the user the source of error.
     */
    public DukeException(String errormessage) {
        super(errormessage);
    }

    public String toString() {
        return "LAMMEEEEEEE...." + super.getMessage() + "\n";
    }
}
