package kwuntalk.exception;


/**
 * Represents an exception when an invalid or unknown command is used in the user input.
 */
public class InvalidCommandException extends KwunTalkException {
    private String command;


    /**
     * Constructor for InvalidCommandException.
     *
     * @param command Command that threw the exception.
     */
    public InvalidCommandException(String command) {
        this.command = command;
    }


    /**
     * Return the string representation of the exception.
     *
     * @return String representation of the exception.
     */
    @Override
    public String toString() {
        return String.format("%s I don't know what '%s' means ;(\nPlease try again.\n",
                super.toString(),
                command);
    }
}
