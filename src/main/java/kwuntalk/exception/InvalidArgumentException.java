package kwuntalk.exception;


/**
 * Represents an exception when an invalid arguments if provided for the command.
 */
public class InvalidArgumentException extends KwunTalkException {
    private String command;

    /**
     * Constructor for InvalidArgumentException.
     *
     * @param command Command that threw the exception.
     */
    public InvalidArgumentException(String command) {
        this.command = command;
    }


    /**
     * Return the string representation of the exception.
     *
     * @return String representation of the exception.
     */
    @Override
    public String toString() {
        return String.format("%s The '%s' command has invalid argument(s)! ;(\nPlease fill it in.\n",
                super.toString(),
                command);
    }
}
